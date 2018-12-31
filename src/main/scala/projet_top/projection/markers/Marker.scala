package projet_top.projection.markers

import java.awt.{BasicStroke, Color, Graphics2D, Shape}

/**
  * Marqueur qui est placé sur la carte à la position de l'objet à afficher.
  * @param color couleur pour le marqueur
  * @param filling remplissage du marqueur
  */
abstract class Marker(color: Color, filling: Filling) {
  /**
    * Méthode interne qui génère la java.awt.Shape finale à partir de la position où placer le marqueur.
    * @param xCenter coordonnée x où placer le marqueur
    * @param yCenter coordonnée y où placer le marqueur
    * @return la java.awt.Shape finale à placer sur l'image
    */
  def shapeFactory(xCenter: Double, yCenter: Double): Shape

  /**
    * Place ce marqueur sur la carte à la position indiquée.
    * @param brush brush permettant de modifier l'image
    * @param x coordonnée x où placer le marqueur
    * @param y coordonnée y où placer le marqueur
    */
  //noinspection RedundantBlock
  def putAt(brush: Graphics2D)(x: Double, y: Double): Unit = {

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
