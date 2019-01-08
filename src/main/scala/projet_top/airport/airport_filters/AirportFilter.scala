package projet_top.airport.airport_filters

import projet_top.airport.Airport

//noinspection RedundantBlock
object AirportFilter {
  /**
    * Wrap la représentation de la contrainte d'un filtre avec du texte.
    * @param constraintsRepr une représentation de la contrainte du filtre
    * @return le texte wrappé
    */
  def fancyWrap(constraintsRepr: String): String = {
    s"AirportFilter[${constraintsRepr}]"
  }
}

/**
  * Classe abstraite pour les filtres d'aéroports
  */
abstract class AirportFilter {
  /**
    * Retourne true ssi le filtre "conserve" le candidat passé en paramètre.
    * @param candidate objet Airport à tester
    * @return true ssi le filtre "conserve" le candidat passé en paramètre
    */
  def accepts(candidate: Airport): Boolean

  /**
    * Retourne true ssi le filtre "rejette" le candidat passé en paramètre.
    * @param candidate objet Airport à tester
    * @return true ssi le filtre "rejette" le candidat passé en paramètre
    */
  def rejects(candidate: Airport): Boolean = !accepts(candidate)

  /**
    * Inverse le filtre courant.
    * @return un nouveau filtre qui accepte l'opposé de ce qu'accepte ce filtre
    */
  def unary_!(): AirportFilter = new CustomAirportFilter(this.rejects, s"!(${this.constraintsRepr})")

  /**
    * Réalise un OU des deux filtres.
    * @param that deuxième élément de la disjonction
    * @return un nouveau filtre qui accepte ce qu'accepte au moins un des deux filtres
    */
  def ||(that: AirportFilter) : AirportFilter = new CustomAirportFilter(candidate => this.accepts(candidate) || that.accepts(candidate), s"(${this.constraintsRepr}) || (${that.constraintsRepr})")

  /**
    * Réalise un ET des deux filtres.
    * @param that deuxième élément de la conjonction
    * @return un nouveau filtre qui accepte ce qu'accepte les deux filtres en même temps
    */
  def &&(that: AirportFilter) : AirportFilter = new CustomAirportFilter(candidate => this.accepts(candidate) && that.accepts(candidate), s"(${this.constraintsRepr}) && (${that.constraintsRepr})")

  /**
    * Retourne une représentation de la contrainte du filtre.
    * @return une représentation de la contrainte du filtre
    */
  def constraintsRepr: String

  /**
    * Aperçu de l'objet
    * @return un aperçu de l'objet
    */
  override def toString: String = {
    AirportFilter.fancyWrap(this.constraintsRepr)
  }
}

/**
  * Classe interne qui permet d'instancier un filtre à partir de la méthode accepts seulement.
  * @param func future méthode accepts pour le filtre qui sera créé
  */
private class CustomAirportFilter(func: Airport => Boolean, val constraintsRepr: String) extends AirportFilter {
  /**
    * Retourne true ssi le filtre "conserve" le candidat passé en paramètre. Basé sur le champ func.
    * @param candidate objet Airport à tester
    * @return true ssi le filtre "conserve" le candidat passé en paramètre
    */
  override def accepts(candidate: Airport): Boolean = func(candidate)
}
