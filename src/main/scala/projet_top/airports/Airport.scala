package projet_top.airports

import projet_top.maps.HasCoordinates

/**
  * Classe immuable qui contient les données relatives à un aéroport. Sert principalement dans la classe
  * AirportDatabase, et possède peu/pas de méthodes
  */
case class Airport(airportId: Int, airportName: String, cityName: String, countryName: String, latitude: Double, longitude: Double)
  extends HasCoordinates {
  require(
    !this.airportName.isEmpty,
    "airport name cannot be empty !"
  )
  require(
    !this.cityName.isEmpty,
    "city name cannot be empty !"
  )
  require(
    !this.countryName.isEmpty,
    "country name cannot be empty !"
  )
}

