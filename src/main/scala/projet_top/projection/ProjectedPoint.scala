package projet_top.projection

sealed abstract class ProjectedPoint
case object OutOfMap extends ProjectedPoint {
  override def toString: String = {
    "ProjectedPoint[<out of map>]"
  }
}
case class OnMap(x: Int, y: Int) extends ProjectedPoint {
  override def toString: String = {
    s"ProjectedPoint[(x: ${this.x}, y: ${this.y})]"
  }
}

