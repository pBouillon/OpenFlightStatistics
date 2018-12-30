package projet_top.projection.projectors

import projet_top.projection.projection_types.Projection
import projet_top.globe.{HasCoordinates, Point}
import projet_top.projection.ProjectedPoint

abstract class Projector {
  /**
    * Centre de la projection (longitude et longitude)
    */
  val center: Point
  /**
    * Type de projection
    */
  val projection: Projection

  /**
    * Retourne le résultat de la projection par ce projecteur de l'objet spécifié sur une carte ayant
    * la largeur spécifiée.
    * @param obj l'objet à projeter
    * @param width largeur de la carte
    * @return résultat de la projection
    */
  def projects(obj: HasCoordinates)(width: Int): ProjectedPoint
}
