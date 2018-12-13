package projet_top.projection.projection_types

/**
  * Représente le type de projection qu'un projecteur pourra réaliser
  */
abstract class Projection {
  /**
    * Nom de la projection.
    * @return le nom de la projection
    */
  def name: String

  /**
    * Courte description de la projection
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
    s"    ratioWH         ${this.ratioWidthHeight}\n" +
    "]"
  }
}
