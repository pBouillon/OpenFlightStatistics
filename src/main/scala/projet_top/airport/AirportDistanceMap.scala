package projet_top.airport

import scala.collection.immutable
import scala.math.{sqrt, pow}

/**
  * Objet compagnon de la classe AirportDistanceMap. Sert à contenir les méthodes et champs statiques.
  */
object AirportDistanceMap {
  val EmptyMapSoNoValue: Double = -1
}

/**
  * Classe qui représente une carte des distances entre les aéroports
  *
  * @param airportIdToAirport map airportId <=> objet Airport, qui contient les aéroports représentés
  *                           dans la carte des distances
  * @param airportIdsToDistance map (airportId1, airportId2) <=> distance qui contient des distances entre
  * les aéroports, indentifiés par leur airportId. Seules les clés (id1, id2) avec id2 > id1 doivent être présentes
  */
//noinspection RedundantBlock,ScalaUnusedSymbol
class AirportDistanceMap(private val airportIdToAirport: immutable.Map[Int, Airport],
                         private val airportIdsToDistance: immutable.Map[(Int, Int), Double]) {

  require(
    this.airportIdsToDistance forall { case ((airportId1, airportId2), distance) => airportId1 < airportId2 },
    "IDs in the airportIdsToDistance map should respect airportId1 < airportId2"
  )

  require(
    this.airportIdsToDistance forall {
      case ((airportId1, airportId2), distance) =>
        this.airportIdToAirport.contains(airportId1) && this.airportIdToAirport.contains(airportId2)
    },
    "IDs indexing the airportIdsToDistance map should all be present in the airportIdToAirport map"
  )

  /**
    * Inverse de la map airportIdToAirport, utilisée par apply(Airport, Airport)
    */
  private val airportToAirportId: immutable.Map[Airport, Int] =
    this.airportIdToAirport map { case (airportId, airport) => (airport, airportId) }

  /**
    * Nombre de pairs d'aéroports dans la map des distances.
    */
  private val pairsNumber: Int = airportIdsToDistance.size

  /**
    * Liste triée des distances.
    */
  private val sortedDistances =
    this.airportIdsToDistance.map({
      case ((airportId1, airportId2), distance) => distance
    }).toList.sortWith({
      case (distance1, distance2) => distance1 < distance2
    })

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

    this.sortedDistances.head
  }

  /**
    * Distance qui sépare les deux aéroports les plus éloignés de la carte.
    */
  lazy val maxDistance: Double = {
    // on prend la dernière valeur de la liste des distances rangées dans l'odre croissant

    this.sortedDistances.last
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
    // On calcule notre médiane selon le nombre d'éléments (pair/impair) dans notre ensemble
    if (this.pairsNumber % 2 == 1)
      this.sortedDistances(this.pairsNumber / 2)
    else {
      val (centerLeft, centerRight) = sortedDistances.splitAt(this.pairsNumber / 2)
      (centerLeft.last + centerRight.head) / 2.0
    }
  }

  /**
    * Écart-type des distances qui séparent les aéroports de la carte.
    */
  lazy val stdDev: Double = {
    val avg = this.avgDistance
    // On calcule l'écart-type et on le renvoie
    sqrt(this.sortedDistances.map({ distance => pow(distance - avg, 2) }).sum / this.pairsNumber)
  }

  /**
    * Retourne la distance entre les deux aéroports choisis, identifiés par leur ID.
    *
    * @param airportIdA ID du premier aéroport
    * @param airportIdB ID du deuxième aéroport
    * @return la distance entre les deux
    */
  def getDistanceBetween(airportIdA: Int)(airportIdB: Int): Double = {
    if (!this.airportIdsToDistance.contains((airportIdA, airportIdB))) {
      throw new NoSuchElementException(
        s"Distance between airport with IDs ${airportIdA} and ${airportIdB} is not present in the map"
      )
    }
    this.airportIdsToDistance((airportIdA, airportIdB))
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
    this.airportIdsToDistance(
      this.airportToAirportId(airportA),
      this.airportToAirportId(airportB)
    )
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
