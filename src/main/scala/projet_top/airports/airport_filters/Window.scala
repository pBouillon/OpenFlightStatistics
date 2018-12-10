package projet_top.airports.airport_filters

import projet_top.airports.Airport
import projet_top.maps.{HasCoordinates, Point}

/**
  * Objet compagnon de la classe Window. Contient les méthodes statiques
  */
object Window {
  /**
    * Case-constructeur pour la classe Window
    * @param coordA premier objet possédant des coordonnées pour délimiter la fenêtre
    * @param coordB second objet possédant des coordonnées pour délimiter la fenêtre
    * @return l'instance du filtre Window correspondante
    */
  def apply(coordA: HasCoordinates, coordB: HasCoordinates): Window = {
    val bottomLatitude = coordA.latitude min coordB.latitude
    val topLatitude = coordA.latitude max coordB.latitude
    val leftLongitude = coordA.longitude min coordB.longitude
    val rightLongitude = coordA.longitude max coordB.longitude

    new Window(Point(bottomLatitude, leftLongitude), Point(topLatitude, rightLongitude))
  }

  /**
    * Case-extracteur pour la classe Window
    * @param window l'instance Window dont extraire les données
    * @return le coin inférieur gauche et supérieur droit délimitant la fenêtre
    */
  def unapply(window: Window): Option[(Point, Point)] = Some(window.bottomLeft, window.topRight)
}

/**
  * N'accepte que les aéroports situés dans une fenêtre délimitée par un coin inférieur gauche et supérieur droit,
  * bordure incluse.
  * @param bottomLeft le coin inférieur gauche de la bordure
  * @param topRight le coin supérieur droit de la bordure
  */
class Window private (val bottomLeft: Point, val topRight: Point) extends AirportFilter {
  /**
    * Retourne true ssi le filtre "conserve" le candidat passé en paramètre.
    * @param candidate objet Airport à tester
    * @return true ssi le filtre "conserve" le candidat passé en paramètre
    */
  override def accepts(candidate: Airport): Boolean = {
    this.bottomLeft.latitude <= candidate.latitude && candidate.latitude <= this.topRight.latitude &&
    this.bottomLeft.longitude <= candidate.longitude && candidate.longitude <= this.topRight.longitude
  }
}
