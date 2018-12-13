package projet_top.projections.projectors

import projet_top.projections.projection_types.ProjectionType
import projet_top.maps.{HasCoordinate, Point}
import projet_top.projections.ProjectedPoint

abstract class Projector {
  /**
    * Centre de la projection (latitude et longitude)
    */
  val center: Point
  /**
    * Type de projection
    */
  val projectionType: ProjectionType

  def projects(obj: HasCoordinate)(width: Int, height: Int): ProjectedPoint
}
