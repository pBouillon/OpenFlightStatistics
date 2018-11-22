package projet_top.airports

import scala.collection.immutable
import scala.math.sqrt

/**
  * Classe qui représente une carte des distances entre les aéroports
  * @param airportIdToAirport map airportId <=> objet Airport, qui contient les aéroports représentés dans la carte des distances
  * @param airportIdsToDist map (airportId1, airportId2) <=> distance qui contient des distances entre les aéroports, indentifiés par leur airportId
  */
class AirportDistanceMap(private val airportIdToAirport: immutable.Map[Int, Airport],
                         private val airportIdsToDist: immutable.Map[(Int, Int), Double]) {
  /**
    * Retourne la distance qui sépare les deux aéroports les plus proches de la carte
    * @return la distance qui sépare les deux aéroports les plus proches de la carte
    */
  def minDistance: Double = {
    // TODO
    0.0
  }

  /**
    * Retourne la distance qui sépare les deux aéroports les plus éloignés de la carte
    * @return la distance qui sépare les deux aéroports les plus éloignés de la carte
    */
  def maxDistance: Double = {
    // TODO
    0.0
  }

  /**
    * Retourne la distance moyenne entre les aéroports de la carte
    * @return la distance moyenne entre les aéroports de la carte
    */
  def avgDistance: Double = {
    // TODO
    0.0
  }

  /**
    * Retourne la distance médiane entre les aéroports de la carte
    * @return la distance médiane entre les aéroports de la carte
    */
  def medianDistance: Double = {
    // On enlève les doublons inutiles et on trie nos distances
    val noDupAirportRecords = airportIdsToDist filter
      { case ((airportId1, airportId2), distance) => airportId1 < airportId2 }
    val noDupSortedDistances = (noDupAirportRecords map
      { case ((airportId1, airportId2), distance) => distance }).toList
      .sortWith({ case (distance, distance2) => distance < distance2})
    // Puis on calcule notre médiane selon le nombre d'éléments (pair/impair) dans notre ensemble
    if (noDupSortedDistances.size % 2 == 1)
      noDupSortedDistances(noDupSortedDistances.size/2)
    else {
      val (a, b) = noDupSortedDistances.splitAt(noDupSortedDistances.size/2)
      (a.last + b.head)/2
    }
  }

  /**
    * Retourne l'écart-type des distances qui séparent les aéroports de la carte
    * @return l'écart-type des distances qui séparent les aéroports de la carte
    */
  def stdDev: Double = {
    // On enlève les doublons inutiles
    val noDup = airportIdsToDist.filter((r :((Int, Int), Double)) => r._1._1 < r._1._2)
    val moy = noDup.foldLeft(0.0)(_ + _._2) / noDup.size
    // On calcule l'écart-type et on le renvoit
    sqrt(noDbl.foldLeft(0.0)((s: Double, r: ((Int,Int), Double)) => s + Math.pow(r._2 - moy, 2)) / noDup.size)
  }

  /**
    * Retourne la distance entre les deux aéroports choisis, identifiés par leur ID.
    * @param airportIdA ID du premier aéroport
    * @param airportIdB ID du deuxième aéroport
    * @return la distance entre les deux
    */
  def apply(airportIdA: Int)(airportIdB: Int): Double = {
    // TODO
    0.0
  }

  /**
    * Retourne la distance entre les deux aéroports choisis
    * @param airportA premier aéroport
    * @param airportB deuxième aéroport
    * @return la distance entre les deux
    */
  def apply(airportA: Airport)(airportB: Airport): Double = {
    // TODO
    0.0
  }
}
