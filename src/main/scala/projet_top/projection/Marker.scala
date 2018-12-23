package projet_top.projection

import java.awt.geom.{Ellipse2D, Rectangle2D}
import java.awt.{BasicStroke, Color, Graphics2D, Shape}

sealed abstract class Marker(color: Color, filling: Filling) {
  def shapeFactory(xCenter: Double, yCenter: Double): Shape

  //noinspection RedundantBlock
  def apply(brush: Graphics2D)(x: Double, y: Double): Unit = {

    brush.setColor(this.color)

    this.filling match {
      case Filled => {
        brush.setStroke(new BasicStroke())
        brush.fill(this.shapeFactory(x, y))
      }
      case Outline(width) => {
        brush.setStroke(new BasicStroke(width))
        brush.draw(this.shapeFactory(x, y))
      }
    }
  }
}

case class Round(color: Color, filling: Filling, size: Double) extends Marker(color, filling) {
  override def shapeFactory(xCenter: Double, yCenter: Double): Shape = {
    val (xUpperLeft, yUpperLeft, width, height) = Utils.centerSizeToULWH(xCenter, yCenter, this.size)
    new Ellipse2D.Double(xUpperLeft, yUpperLeft, width, height)
  }
}

case class Rectangle(color: Color, filling: Filling, width: Double, height: Double) extends Marker(color, filling) {
  override def shapeFactory(xCenter: Double, yCenter: Double): Shape = {
    val (xUpperLeft, yUpperLeft, width, height) = Utils.centerWHToULWH(xCenter, yCenter, this.width, this.height)
    new Rectangle2D.Double(xUpperLeft, yUpperLeft, width, height)
  }
}

case class Square(color: Color, filling: Filling, size: Double) extends Marker(color, filling) {
  override def shapeFactory(xCenter: Double, yCenter: Double): Shape = {
    val (xUpperLeft, yUpperLeft, width, height) = Utils.centerSizeToULWH(xCenter, yCenter, this.size)
    new Rectangle2D.Double(xUpperLeft, yUpperLeft, width, height)
  }
}

sealed trait Filling
case object Filled extends Filling
case class Outline(width: Float) extends Filling
