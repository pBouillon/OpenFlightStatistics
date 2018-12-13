package projet_top.projections

sealed abstract class ProjectedPoint
case object OutOfMap
case class OnMap(x: Int, y: Int)(width: Int, height: Int)

