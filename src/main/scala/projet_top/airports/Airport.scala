package projet_top.airports

/**
  * Objet compagnon de la classe Airport. Contient les méthodes et champs statiques
  * pour cette dernière
  */
object Airport {
  /**
    * Retourne la distance entre les deux aéroports passés en paramètres.
    *
    * @param airportA données (objet projet_top.airports.Airport) pour le premier aéroport
    * @param airportB données (objet projet_top.airports.Airport) pour le second aéroport
    * @return la distance en ??? entre les deux aéroports choisis TODO ???
    */
  def distance(airportA: Airport, airportB: Airport): Double = {
    // TODO
    0.0
  }
}

/**
  * Classe immuable qui contient les données relatives à un aéroport. Sert principalement dans la classe
  * AirportDatabase, et possède peu/pas de méthodes
  */
case class Airport(airportId: Int, name: String, city: String, countryName: String, latitude: Double, longitude: Double)

