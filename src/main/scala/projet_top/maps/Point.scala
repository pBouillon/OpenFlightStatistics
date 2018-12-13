package projet_top.maps

/**
  * Représente un point quelconque sur la carte.
  * @param latitude latitude en degrés
  * @param longitude longitude en degrés
  */
case class Point(latitude: Double, longitude: Double) extends HasCoordinates
