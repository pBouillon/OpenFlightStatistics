package projet_top.projections.projection_types

/**
  * Représente le type de projection qu'un projecteur pourra réaliser
  */
trait ProjectionType {
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
    * Affichage de l'objet sous forme de chaîne
    * @return un affichage de l'objet sous forme de chaîne
    */
  override def toString: String = {
    this.name + "\n" + ("=" * this.name.length) + "\n\n" + this.description
  }
}
