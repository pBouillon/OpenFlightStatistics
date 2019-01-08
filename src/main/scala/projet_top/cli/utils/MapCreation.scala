package projet_top.cli.utils

import java.awt.Color

import projet_top.globe.Point
import projet_top.projection.backmap_providers.{BackmapProvider, D3BackMapProvider}

object MapCreation {
  // map privoder
  val defaultBackmapProvider: BackmapProvider =
    new D3BackMapProvider("http://d3backmapprovider.westeurope.azurecontainer.io:8080/")

  // marker
  val defaultMarkerColor: Color = Color.blue
  val defaultMarkerOutlineThickness: Int = 2
  val defaultMarkerSize: Int = 5

  // image size
  val defaultWidth: Int = 4000
  val minWidth: Int = 100
  val maxWidth: Int = 8000

  // globe center
  val center: Point = Point(.0, .0)
}
