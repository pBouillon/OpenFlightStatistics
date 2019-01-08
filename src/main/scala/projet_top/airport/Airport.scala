package projet_top.airport

import projet_top.globe.HasCoordinates

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

  /**
    * Aperçu de l'objet
    * @return un aperçu de l'objet
    */
  override def toString: String = {
    "Airport [\n" +
    s"    airportId       ${this.airportId}\n" +
    s"    airportName     ${this.airportName}\n" +
    s"    cityName        ${this.cityName}\n" +
    s"    countryName     ${this.countryName}\n" +
    s"    latitude        ${this.latitude}\n" +
    s"    longitude       ${this.longitude}\n" +
    "]"
  }
}

