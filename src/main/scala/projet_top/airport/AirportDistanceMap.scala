package projet_top.airport

import projet_top.globe.Utils.distance

import scala.util.Sorting.quickSort
import scala.math.{pow, sqrt}

/**
  * Objet compagnon de la classe AirportDistanceMap. Sert à contenir les méthodes et champs statiques.
  */
object AirportDistanceMap {
  /**
    * Valeur retournée par les fonctions statistiques quand l'AirportDistanceMap ne contient aucune paire.
    */
  val EmptyMapSoNoValue: Double = -1
}

/**
  * Classe qui représente une carte des distances entre les aéroports
  *
  * @param airportDatabase base d'aéroports à partir de laquelle créer la carte
  *                        des distances
  */
//noinspection RedundantBlock,ScalaUnusedSymbol
class AirportDistanceMap(private val airportDatabase: AirportDatabase) {
  /**
    * Nombre de pairs d'aéroports dans la map des distances.
    */
  private val pairsNumber: Int = (this.airportDatabase.airportIdToAirport.size * (this.airportDatabase.airportIdToAirport.size - 1)) / 2

  /**
    * Liste triée des distances.
    */
  private val sortedDistances = Array.ofDim[Double](pairsNumber)
  private[this] var i = 0
  for ((airportId1, airport1) <- this.airportDatabase.airportIdToAirport) {
    for ((airportId2, airport2) <- this.airportDatabase.airportIdToAirport) {
      if (airportId1 < airportId2) {
        this.sortedDistances(i) = distance(airport1, airport2)
        i += 1
      }
    }
  }
  quickSort(sortedDistances)

  //----------------------------------------------------------------------------
  // METHOD-LIKE FIELDS
  //----------------------------------------------------------------------------

  /**
    * Indique si la map des distances contient des pairs d'aéroports ou non.
    */
  val containsPairs: Boolean = {
    this.pairsNumber > 0
  }

  /**
    * Distance qui sépares les deux aéroports les plus proches de la carte.
    */
  lazy val minDistance: Double = {
    // on prend la première valeur de la liste des distances rangées dans l'odre croissant
    if (this.containsPairs) {
      this.sortedDistances.head
    } else {
      AirportDistanceMap.EmptyMapSoNoValue
    }
  }

  /**
    * Distance qui sépare les deux aéroports les plus éloignés de la carte.
    */
  lazy val maxDistance: Double = {
    // on prend la dernière valeur de la liste des distances rangées dans l'odre croissant
    if (this.containsPairs) {
      this.sortedDistances.last
    } else {
      AirportDistanceMap.EmptyMapSoNoValue
    }
  }

  /**
    * Distance moyenne entre les aéroports de la carte.
    */
  lazy val avgDistance: Double = {
    // somme des distances de la liste sur la taille de la liste ce qui donne la moyenne
    if (this.containsPairs) {
      this.sortedDistances.sum / this.pairsNumber
    } else {
      AirportDistanceMap.EmptyMapSoNoValue
    }
  }

  /**
    * Distance médiane entre les aéroports de la carte.
    */
  lazy val medianDistance: Double = {
    if (this.containsPairs) {
      // On calcule notre médiane selon le nombre d'éléments (pair/impair) dans notre ensemble
      if (this.pairsNumber % 2 == 1)
        this.sortedDistances(this.pairsNumber / 2)
      else {
        val (centerLeft, centerRight) = sortedDistances.splitAt(this.pairsNumber / 2)
        (centerLeft.last + centerRight.head) / 2.0
      }
    } else {
      AirportDistanceMap.EmptyMapSoNoValue
    }
  }

  /**
    * Écart-type des distances qui séparent les aéroports de la carte.
    */
  lazy val stdDev: Double = {
    if (this.containsPairs) {
      val avg = this.avgDistance
      // On calcule l'écart-type et on le renvoie
      sqrt(this.sortedDistances.map({ distance => pow(distance - avg, 2) }).sum / this.pairsNumber)
    } else {
      AirportDistanceMap.EmptyMapSoNoValue
    }
  }

  /**
    * Retourne la distance entre les deux aéroports choisis, identifiés par leur ID.
    *
    * @param airportIdA ID du premier aéroport
    * @param airportIdB ID du deuxième aéroport
    * @return la distance entre les deux
    */
  def getDistanceBetween(airportIdA: Int)(airportIdB: Int): Double = {
    if (!this.airportDatabase.contains(airportIdA)) {
      throw new NoSuchElementException(
        "The first airport specified is not present in the map"
      )
    }
    if (!this.airportDatabase.contains(airportIdB)) {
      throw new NoSuchElementException(
        "The second airport specified is not present in the map"
      )
    }
    distance(this.airportDatabase.getAirportById(airportIdA), this.airportDatabase.getAirportById(airportIdB))
  }

  /**
    * Retourne la distance entre les deux aéroports choisis, identifiés par leur ID.
    * Identique à getDistanceBetween(Int, Int)
    *
    * @param airportIdA ID du premier aéroport
    * @param airportIdB ID du deuxième aéroport
    * @return la distance entre les deux
    */
  def apply(airportIdA: Int)(airportIdB: Int): Double = this.getDistanceBetween(airportIdA)(airportIdB)

  /**
    * Retourne la distance entre les deux aéroports choisis.
    *
    * @param airportA premier aéroport
    * @param airportB deuxième aéroport
    * @return la distance entre les deux
    */
  def getDistanceBetween(airportA: Airport)(airportB: Airport): Double = {
    if (!this.airportDatabase.contains(airportA)) {
      throw new NoSuchElementException(
        "The first airport specified is not present in the map"
      )
    }
    if (!this.airportDatabase.contains(airportB)) {
      throw new NoSuchElementException(
        "The second airport specified is not present in the map"
      )
    }
    this.getDistanceBetween(airportA.airportId)(airportB.airportId)
  }

  /**
    * Retourne la distance entre les deux aéroports choisis. Identique à getDistanceBetween(Airport, Airport)
    *
    * @param airportA premier aéroport
    * @param airportB deuxième aéroport
    * @return la distance entre les deux
    */
  def apply(airportA: Airport)(airportB: Airport): Double = this.getDistanceBetween(airportA)(airportB)

  /**
    * Aperçu de l'objet
    * @return un aperçu de l'objet
    */
  override def toString: String = {
    "AirportDistanceMap [\n" +
    s"    pairs           ${this.pairsNumber}\n" +
    s"    min             ${this.minDistance}\n" +
    s"    max             ${this.maxDistance}\n" +
    s"    avg             ${this.avgDistance}\n" +
    s"    median          ${this.medianDistance}\n" +
    s"    stdDev          ${this.stdDev}\n" +
    "]"
  }
}
