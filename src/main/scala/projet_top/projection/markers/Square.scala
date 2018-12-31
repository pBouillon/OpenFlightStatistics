package projet_top.projection.markers

import java.awt.geom.Rectangle2D
import java.awt.{Color, Shape}

import projet_top.projection.Utils

/**
  * Marqueur carré.
  * @param color couleur pour le marqueur
  * @param filling remplissage du marqueur
  * @param size taille du côté du carré en pixels
  */
case class Square(color: Color, filling: Filling)(size: Double) extends Marker(color, filling) {
  /**
    * Méthode interne qui génère la java.awt.Shape finale à partir de la position où placer le marqueur.
    * @param xCenter coordonnée x où placer le marqueur
    * @param yCenter coordonnée y où placer le marqueur
    * @return la java.awt.Shape finale à placer sur l'image
    */
  override def shapeFactory(xCenter: Double, yCenter: Double): Shape = {
    val (xUpperLeft, yUpperLeft, width, height) = Utils.centerSizeToULWH(xCenter, yCenter, this.size)
    new Rectangle2D.Double(xUpperLeft, yUpperLeft, width, height)
  }
}
