package projet_top.airports

import scala.collection.immutable
import scala.math.{sqrt, pow}

/**
  * Classe qui représente une carte des distances entre les aéroports
  *
  * @param airportIdToAirport map airportId <=> objet Airport, qui contient les aéroports représentés
  *                           dans la carte des distances
  * @param airportIdsToDist map (airportId1, airportId2) <=> distance qui contient des distances entre
  *                         les aéroports, indentifiés par leur airportId
  */
//noinspection RedundantBlock,ScalaUnusedSymbol
class AirportDistanceMap(private val airportIdToAirport: immutable.Map[Int, Airport],
                         private val airportIdsToDist: immutable.Map[(Int, Int), Double]) {

  private val airportToAirportId: immutable.Map[Airport, Int] =
    this.airportIdToAirport map { case (airportId, airport) => (airport, airportId) }

  private val noDupAirportRecords = airportIdsToDist filter
    { case ((airportId1, airportId2), distance) => airportId1 < airportId2 }
  private val noDupLength = this.noDupAirportRecords.size
  private val noDupSortedDistances = (noDupAirportRecords map
    { case ((airportId1, airportId2), distance) => distance }).toList
    .sortWith({ case (distance, distance2) => distance < distance2})

/**
    * Retourne la distance qui sépare les deux aéroports les plus proches de la carte
    *
    * @return la distance qui sépare les deux aéroports les plus proches de la carte
    */
  def minDistance: Double = {
    // TODO
    0.0
  }

  /**
    * Retourne la distance qui sépare les deux aéroports les plus éloignés de la carte
    *
    * @return la distance qui sépare les deux aéroports les plus éloignés de la carte
    */
  def maxDistance: Double = {
    // TODO
    0.0
  }

  /**
    * Retourne la distance moyenne entre les aéroports de la carte
    *
    * @return la distance moyenne entre les aéroports de la carte
    */
  def avgDistance: Double = {
    // TODO
    0.0
  }

  /**
    * Retourne la distance médiane entre les aéroports de la carte
    *
    * @return la distance médiane entre les aéroports de la carte
    */
  def medianDistance: Double = {
    // On calcule notre médiane selon le nombre d'éléments (pair/impair) dans notre ensemble
    if (this.noDupLength % 2 == 1)
      this.noDupSortedDistances(this.noDupLength / 2)
    else {
      val (centerLeft, centerRight) = noDupSortedDistances.splitAt(this.noDupLength / 2)
      (centerLeft.last + centerRight.head) / 2.0
    }
  }

  /**
    * Retourne l'écart-type des distances qui séparent les aéroports de la carte
    *
    * @return l'écart-type des distances qui séparent les aéroports de la carte
    */
  def stdDev: Double = {
    val avg = this.avgDistance
    // On calcule l'écart-type et on le renvoie
    sqrt((this.noDupSortedDistances map { distance => pow(distance - avg, 2) }).sum / this.noDupLength)
  }

  /**
    * Retourne la distance entre les deux aéroports choisis, identifiés par leur ID.
    *
    * @param airportIdA ID du premier aéroport
    * @param airportIdB ID du deuxième aéroport
    * @return la distance entre les deux
    */
  def apply(airportIdA: Int)(airportIdB: Int): Double = {
    if (!this.airportIdsToDist.contains((airportIdA, airportIdB))) {
      throw new NoSuchElementException(
        s"Distance between airports with IDs ${airportIdA} and ${airportIdB} is not present in the map"
      )
    }
    this.airportIdsToDist((airportIdA, airportIdB))
  }

  /**
    * Retourne la distance entre les deux aéroports choisis
    *
    * @param airportA premier aéroport
    * @param airportB deuxième aéroport
    * @return la distance entre les deux
    */
  def apply(airportA: Airport)(airportB: Airport): Double = {
    if (!this.airportToAirportId.contains(airportA)) {
      throw new NoSuchElementException(
        "The first airport specified is not present in the map"
      )
    }
    if (!this.airportToAirportId.contains(airportB)) {
      throw new NoSuchElementException(
        "The second airport specified is not present in the map"
      )
    }
    this.airportIdsToDist(
      this.airportToAirportId(airportA),
      this.airportToAirportId(airportB)
    )
  }
}
