package projet_top.globe

/**
  * Représente un point quelconque sur la carte.
  * @param latitude longitude en degrés
  * @param longitude longitude en degrés
  */
case class Point(latitude: Double, longitude: Double) extends HasCoordinates {
  /**
    * Aperçu de l'objet
    * @return un aperçu de l'objet
    */
  override def toString: String = {
    s"<lat: ${this.latitude}, long: ${this.longitude}>"
  }
}
