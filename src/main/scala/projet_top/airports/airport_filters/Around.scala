package projet_top.airports.airport_filters

import projet_top.airports.Airport
import projet_top.maps.HasCoordinates
import projet_top.maps.Utils.distance

/**
  * Ne conserve que les aéroports situés dans le disque de centre center et de rayon radius en km, bordure incluse.
  * @param center centre de la zone désirée
  * @param radius rayon de la zone désirée
  */
case class Around(center: HasCoordinates, radius: Double) extends AirportFilter {
  /**
    * Retourne true ssi le filtre "conserve" le candidat passé en paramètre.
    * @param candidate objet Airport à tester
    * @return true ssi le filtre "conserve" le candidat passé en paramètre
    */
  override def accepts(candidate: Airport): Boolean = {
    distance(center, candidate) <= radius
  }
}
