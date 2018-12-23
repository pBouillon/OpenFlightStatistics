package projet_top.globe

import scala.math.{acos, cos, sin, toRadians}

/**
  * Méthodes utilitaires sur les positions géographiques, les cartes...
  */
object Utils {
  // https://fr.wikipedia.org/wiki/Rayon_de_la_Terre
  /**
    * Rayon de la terre en km.
    */
  val EarthRadius: Double = 6371.009

  /**
    * Calcule la distance en km entre les deux objets spécifiés
    * @param objA premier objet. Doit avoir une position
    * @param objB deuxième objet. Doit avoir une position
    * @return la distance en km entre les deux
    */
  def distance(objA: HasCoordinates, objB: HasCoordinates): Double = {
    // Conversion en radians
    val latitudeRadA = toRadians(objA.latitude)
    val latitudeRadB = toRadians(objB.latitude)
    val longitudeRadA = toRadians(objA.longitude)
    val longitudeRadB = toRadians(objB.longitude)
    // Calcul de la distance d'après la formule proposée à l'adresse
    // https://fr.wikipedia.org/wiki/Trigonom%C3%A9trie_sph%C3%A9rique
    val deltaLongitudeRad = longitudeRadB - longitudeRadA

    EarthRadius * acos(
      sin(latitudeRadA) * sin(latitudeRadB) +
        cos(latitudeRadA) * cos(latitudeRadB) * cos(deltaLongitudeRad)
    )
  }
}
