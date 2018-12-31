package projet_top.projection.projectors

import projet_top.globe.{HasCoordinates, Point}
import projet_top.projection.ProjectedPoint

abstract class Projector {
  /**
    * Centre de la projection (longitude et longitude)
    */
  val center: Point

  /**
    * Nom de la projection.
    * @return le nom de la projection
    */
  def name: String

  /**
    * Courte description de la projection.
    * @return une courte description de la projection
    */
  def description: String

  /**
    * Rapport largeur / hauteur des cartes générées par ce type de projection.
    * @return le rapport largeur / hauteur des cartes générées par ce type de projection.
    */
  def ratioWidthHeight: Double

  /**
    * Aperçu de l'objet
    * @return un aperçu de l'objet
    */
  override def toString: String = {
    "Projection [\n" +
      s"    name            ${this.name}\n" +
      s"    description     ${this.description.split("\n").mkString("\n" + (" " * 20))}\n" +
      s"    ratioWH         ${this.ratioWidthHeight}\n\n" +
      s"    center          ${this.center}\n" +
      "]"
  }

  /**
    * Retourne le résultat de la projection par ce projecteur de l'objet spécifié sur une carte ayant
    * la largeur spécifiée.
    * @param obj l'objet à projeter
    * @param width largeur de la carte
    * @return résultat de la projection
    */
  def projects(obj: HasCoordinates)(width: Int): ProjectedPoint
}
