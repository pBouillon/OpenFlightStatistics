package projet_top.airports

import math.{cos, sin, acos, toRadians}

/**
  * Objet compagnon de la classe Airport. Contient les méthodes et champs statiques
  * pour cette dernière
  */
object Airport {
  // https://fr.wikipedia.org/wiki/Rayon_de_la_Terre
  val EarthRadius: Double = 6371.009

  /**
    * Retourne la distance entre les deux aéroports passés en paramètres.
    *
    * @param airportA données (objet Airport) pour le premier aéroport
    * @param airportB données (objet Airport) pour le second aéroport
    * @return la distance en ??? entre les deux aéroports choisis TODO ???
    */
  def distance(airportA: Airport, airportB: Airport): Double = {
    // Conversion en radians
    val latitudeRadA = toRadians(airportA.latitude)
    val latitudeRadB = toRadians(airportB.latitude)
    val longitudeRadA = toRadians(airportA.longitude)
    val longitudeRadB = toRadians(airportB.longitude)
    // Calcul de la distance d'après la formule proposée à l'adresse
    // https://fr.wikipedia.org/wiki/Trigonom%C3%A9trie_sph%C3%A9rique
    val deltaLongitudeRad = longitudeRadB - longitudeRadA

    EarthRadius * acos(
      sin(latitudeRadA) * sin(latitudeRadB) +
      cos(latitudeRadA) * cos(latitudeRadB) * cos(deltaLongitudeRad)
    )
  }
}

/**
  * Classe immuable qui contient les données relatives à un aéroport. Sert principalement dans la classe
  * AirportDatabase, et possède peu/pas de méthodes
  */
case class Airport(airportId: Int, name: String, city: String, countryName: String, latitude: Double, longitude: Double) {
  require(
    !name.isEmpty,
    "airport name cannot be empty !"
  )
  require(
    !city.isEmpty,
    "city name cannot be empty !"
  )
  require(
    !countryName.isEmpty,
    "country name cannot be empty !"
  )
  require(
    -90 <= latitude && latitude <= 90,
    "latitude must be in range [-90, 90]°"
  )
  require(
    -180 <= longitude && longitude <= 180,
    "longitude must be in range [-180, 180]°"
  )
}

