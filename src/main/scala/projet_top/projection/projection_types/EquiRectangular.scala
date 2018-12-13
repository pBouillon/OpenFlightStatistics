package projet_top.projection.projection_types

object EquiRectangular extends Projection {
  override def name: String = {
    "EquiRectangular"
  }

  override def description: String = {
    "x = long\n" +
    "y = lat"
  }

  override def ratioWidthHeight: Double = 2.0
}
