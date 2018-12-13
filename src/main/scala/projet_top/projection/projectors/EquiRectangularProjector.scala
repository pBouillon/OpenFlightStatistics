package projet_top.projection.projectors
import projet_top.globe.{HasCoordinates, Point}
import projet_top.projection.{OutOfMap, ProjectedPoint}
import projet_top.projection.projection_types.{EquiRectangular, Projection}

class EquiRectangularProjector(centerRaw: HasCoordinates) extends Projector {
  override val center: Point = centerRaw.toPoint

  override val projection: Projection = EquiRectangular

  override def projects(obj: HasCoordinates)(width: Int): ProjectedPoint = {
    // TODO
    OutOfMap
  }
}
