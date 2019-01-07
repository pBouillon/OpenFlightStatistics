package projet_top.projection.markers

import java.awt.{Color, Shape}
import java.awt.geom.Rectangle2D

import projet_top.projection.Utils

/**
  * Marqueur en forme rectangle.
  * @param color couleur pour le marqueur
  * @param filling remplissage du marqueur
  * @param width largeur du rectangle en pixel
  * @param height hauteur du rectangle en pixel
  */
case class Rectangle(color: Color, filling: Filling)(width: Double, height: Double) extends Marker(color, filling) {
  /**
    * Méthode interne qui génère la java.awt.Shape finale à partir de la position où placer le marqueur.
    * @param xCenter coordonnée x où placer le marqueur
    * @param yCenter coordonnée y où placer le marqueur
    * @return la java.awt.Shape finale à placer sur l'image
    */
  override def shapeFactory(xCenter: Double, yCenter: Double): Shape = {
    val (xUpperLeft, yUpperLeft, width, height) = Utils.centerWHToULWH(xCenter, yCenter, this.width, this.height)
    new Rectangle2D.Double(xUpperLeft, yUpperLeft, width, height)
  }
}
