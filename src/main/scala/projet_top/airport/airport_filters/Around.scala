package projet_top.airport.airport_filters

import projet_top.airport.Airport
import projet_top.globe.{HasCoordinates, Point}
import projet_top.globe.Utils.distance

/**
  * Objet compagnon de la classe Around. Contient les méthodes statiques.
  */
object Around {
  /**
    * Case-constructeur pour Around.
    * @param center objet possédant des coordonnées qui servira de centre pour la zone
    * @param radius rayon de la zone en km
    * @return le filtre correspondant
    */
  def apply(center: HasCoordinates, radius: Double): Around = {
    new Around(center.toPoint, radius)
  }

  /**
    * Case-extracteur pour la classe Around.
    * @param around instance de la classe dont on veut extraire les valeurs
    * @return le centre (objet Point) et le rayon du filtre
    */
  def unapply(around: Around): Option[(Point, Double)] = {
    Some((around.center, around.radius))
  }
}

/**
  * N'accepte que les aéroports situés dans la zone de centre center et de rayon radius (en km), bordure incluse.
  * @param center objet Point qui représente le centre de la zone
  * @param radius rayon en km de la zone
  */
class Around private (val center: Point, val radius: Double) extends AirportFilter {
  /**
    * Retourne true ssi le filtre "conserve" le candidat passé en paramètre, ie
    * si le candidat est situé dans la zone choisie.
    * @param candidate objet Airport à tester
    * @return true ssi le filtre "conserve" le candidat passé en paramètre
    */
  override def accepts(candidate: Airport): Boolean = {
    distance(this.center, candidate) <= radius
  }

  /**
    * Retourne une représentation de la contrainte du filtre.
    * @return une représentation de la contrainte du filtre
    */
  override def constraintsRepr: String = {
    s"airport in Circle(${this.center}, ${this.radius})"
  }
}
