if (typeof fetch !== 'function') {
  global.fetch = require('node-fetch-polyfill')
}

const fs = require('fs')
const express = require('express')
const jsdom = require('jsdom')
const { convert } = require('convert-svg-to-png')

const app = express()
const port = 3000

app.get('/', async (req, res) => {
  try {
    const { type, data } = await serveMap(req, res)
    res.set('Content-Type', type)
    res.send(data)
  } catch (error) {
    console.log(error)
    res.send(error)
  }
})

app.listen(port, (err) => {
  if (err) {
    return console.log(`# Error: <${err}>`)
  }
  console.log(`# Server listening on: ${port}`)
})

//-----------------------------------------------------------------------------

function parseSPositiveInt (str) {
  if (/^[1-9][0-9]+$/.test(str)) {
    return Number(str)
  } else {
    throw `"${str}" is a not a strictly positive int`
  }
}

function parseStrictFloat (str) {
  if (/^(\-|\+)?[0-9]+(\.[0-9]+)?$/.test(str)) {
    return Number(str)
  } else {
    throw `"${str}" is not a strict float`
  }
}

function parseLatitude (str) {
  const value = parseStrictFloat(str)
  if (value <= -90.0 || value > 90.0) {
    throw `${value} is not a valid latitude`
  } else {
    return value
  }
}

function parseLongitude (str) {
  const value = parseStrictFloat(str)
  if (value <= -180.0 || value > 180.0) {
    throw `${value} is not a valid longitude`
  } else {
    return value
  }
}

function fromBase64 (data) {
  return Buffer.from(data, 'base64').toString('binary')
}

function parseFormat (str) {
  if (str === 'svg' || str === 'png') {
    return str
  } else {
    throw `format ${str} is not available`
  }
}

function notSet(data) {
  return (data === '' || data === undefined || data === null)
}

const projectionToMethod = {
  equirectangular: 'geoEquirectangular',
  azimuthalequalarea: 'geoAzimuthalEqualArea',
  azimuthalequidistant: 'geoAzimuthalEquidistant',
  gnomonic: 'geoGnomonic',
  orthographic: 'geoOrthographic',
  stereographic: 'geoStereographic',
  equalearth: 'geoEqualEarth',
  conicconformal: 'geoConicConformal',
  conicequalarea: 'geoConicEqualArea',
  conicequidistant: 'geoConicEquidistant',
  equirectangular: 'geoEquirectangular',
  mercator: 'geoMercator',
  mercator: 'geoTransverseMercator',
  naturalearth1: 'geoNaturalEarth1'
}

const defaultFormat = 'png'
const defaultWidth = 1000
const defaultHeight = 500
const defaultCenterLongitude = 0.0
const defaultCenterLatitude = 0.0
const defaultProjection = 'equirectangular'
const defaultStyle = fs.readFileSync('./mapStyle.css')
const defaultMapJsonDataUrl =
  'https://gist.githubusercontent.com/tbagrel1/56723b6c513afae05c5ba59f85a32fcc/raw/bb3e89a635c80258e991f9ac6aa8d253f6f56941/custom.geo.json'

async function serveMap (req, res) {
  const width = notSet(req.query.width) ?
    defaultWidth : parseSPositiveInt(req.query.width)
  const height = notSet(req.query.height) ?
    defaultHeight : parseSPositiveInt(req.query.height)
  const centerLongitude = notSet(req.query.long) ?
    defaultCenterLongitude : parseLongitude(req.query.long)
  const centerLatitude = notSet(req.query.lat) ?
    defaultCenterLatitude : parseLatitude(req.query.lat)
  const projection = notSet(req.query.proj) ?
    defaultProjection : req.query.proj
  const style = notSet(req.query.style) ?
    defaultStyle : fromBase64(req.query.style)
  const mapJsonDataUrl = notSet(req.query.dataurl) ?
    defaultMapJsonDataUrl : fromBase64(req.query.dataurl)
  const projectionMethod = projectionToMethod[projection]
  const svgMap = await createSVGMap(
    width, height, centerLongitude, centerLatitude, projectionMethod, style,
    mapJsonDataUrl)
  const format = notSet(req.query.format) ?
    defaultFormat : parseFormat(req.query.format)

  if (format == 'png') {
    return ({
      type: 'image/png',
      data: await convert(svgMap)
    })
  } else {
    return ({
      type: 'image/svg+xml',
      data: svgMap
    })
  }
}

async function createSVGMap (
  width, height, centerLongitude, centerLatitude, projectionMethod, style,
  mapJsonDataUrl) {
  const HtmlTemplate = '<!DOCTYPE html><html><head></head><body></body></html>'

  const { window } = new jsdom.JSDOM(HtmlTemplate)
  // Adding d3.min.js dependancy
  global.window = window
  global.document = window.document
  const d3 = require('d3')

  const mapJsonData = await d3.json(mapJsonDataUrl)

  const svg = d3
    .select('body')
    .append('svg')
    .attr('width', width)
    .attr('height', height)
  svg
    .append('style')
    .text(style)
  const projection = d3[projectionMethod]()
    .rotate([-centerLongitude, -centerLatitude])
    .fitSize([width, height], mapJsonData)
  const path = d3
    .geoPath()
    .projection(projection)
  const countriesGroup = svg
    .append('g')
    .attr('id', 'map')
  countriesGroup
    .selectAll('path')
    .data(mapJsonData.features)
    .enter()
    .append('path')
    .attr('d', path)
    .attr('id', function (d, i) {
      return 'country' + d.properties.iso_a3
    })
    .attr('class', 'country')

    return d3.select('body').html()
}
