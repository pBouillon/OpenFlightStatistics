package projet_top.airports.airport_filters

import projet_top.airports.Airport

/**
  * Interface pour les filtres d'aéroports
  */
trait AirportFilter {
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
  def unary_!(): AirportFilter = CustomAirportFilter(this.rejects)

  /**
    * Réalise un OU des deux filtres.
    * @param that deuxième élément de la disjonction
    * @return un nouveau filtre qui accepte ce qu'accepte au moins un des deux filtres
    */
  def ||(that: AirportFilter) : AirportFilter = CustomAirportFilter(candidate => this.accepts(candidate) || that.accepts(candidate))

  /**
    * Réalise un ET des deux filtres.
    * @param that deuxième élément de la conjonction
    * @return un nouveau filtre qui accepte ce qu'accepte les deux filtres en même temps
    */
  def &&(that: AirportFilter) : AirportFilter = CustomAirportFilter(candidate => this.accepts(candidate) && that.accepts(candidate))
}

/**
  * Classe interne qui permet d'instancier un filtre à partir de la méthode accepts seulement.
  * @param func future méthode accepts pour le filtre qui sera créé
  */
private case class CustomAirportFilter(func: Airport => Boolean) extends AirportFilter {
  override def accepts(candidate: Airport): Boolean = func(candidate)
}
