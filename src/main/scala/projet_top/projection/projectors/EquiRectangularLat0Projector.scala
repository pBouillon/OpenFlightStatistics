package projet_top.projection.projectors

import projet_top.globe.{HasCoordinates, Point}
import projet_top.projection.{OnMap, ProjectedPoint, Utils}

class EquiRectangularLat0Projector(centerRaw: HasCoordinates) extends Projector {
  /**
    * Centre de la projection (longitude et longitude)
    */
  override val center: Point = centerRaw.toPoint

  /**
    * Nom de la projection.
    */
  override val name: String = "Equirectangular (Latitude 0 centered)"

  /**
    * Courte description de la projection.
    */
  override val description: String = "Equirectangular projection preceeded by a horizontal-only rotation to adjust projection center (ie center latitude is always 0)."

  /**
    * Rapport largeur / hauteur des cartes générées par ce type de projection.
    */
  override val ratioWidthHeight: Double = 2.0

  /**
    * Fonction permettant de faire la rotation par rapport au centre chosi.
    */
  private val rotator = Utils.rotator(0, -this.center.longitude)

  /**
    * Retourne le résultat de la projection par ce projecteur de l'objet spécifié sur une carte ayant
    * la largeur spécifiée.
    *
    * @param obj l'objet à projeter
    * @param width largeur de la carte
    * @return résultat de la projection
    */
  override def projects(obj: HasCoordinates)(width: Int): ProjectedPoint = {
    val height = Math.round(width / this.ratioWidthHeight)

    val rotatedObj = this.rotator(obj)

    val x = (rotatedObj.longitude + 180.0) / 360.0 * width
    val y = (-rotatedObj.latitude + 90) / 180.0 * height
    OnMap(x, y)
  }
}
