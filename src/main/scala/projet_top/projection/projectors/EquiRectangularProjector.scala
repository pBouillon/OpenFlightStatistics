package projet_top.projection.projectors
import projet_top.globe.{HasCoordinates, Point}
import projet_top.projection.{OnMap, ProjectedPoint, Utils}
import projet_top.projection.projection_types.{EquiRectangular, Projection}

// image axes
//  +-------> x
//  |
//  |
// \/
//  y

class EquiRectangularProjector(centerRaw: HasCoordinates) extends Projector {
  override val center: Point = centerRaw.toPoint

  override val projection: Projection = EquiRectangular

  override def projects(obj: HasCoordinates)(width: Int): ProjectedPoint = {
    val height = Math.round(width / this.projection.ratioWidthHeight)

    val relLatitude = Utils.normLatitude(obj.latitude - this.center.latitude)
    val relLongitude = Utils.normLongitude(obj.longitude - this.center.longitude)

    val x = (relLongitude + 180.0) / 360.0 * width
    val y = (-relLatitude + 90) / 180.0 * height

    OnMap(x, y)
  }
}
