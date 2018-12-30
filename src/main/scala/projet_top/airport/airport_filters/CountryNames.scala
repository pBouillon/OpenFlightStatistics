package projet_top.airport.airport_filters

import projet_top.airport.Airport

/**
  * Filtre qui accepte seulement les aéroports situés dans la liste des pays choisie.
  * @param countryNames la liste des nom de pays dont on veut connaître les aéroports
  */
case class CountryNames(countryNames: List[String]) extends AirportFilter {
  /**
    * Retourne true ssi le filtre "conserve" le candidat passé en paramètre, ie
    * si le candidat est dans un des pays de la liste countryNames.
    * @param candidate objet Airport à tester
    * @return true ssi le filtre "conserve" le candidat passé en paramètre
    * vérifie si le pays choisit est dans la liste des pays
    */
  def accepts(candidate: Airport): Boolean = {
    countryNames.contains(candidate.countryName)
  }

  /**
    * Retourne une représentation de la contrainte du filtre.
    * @return une représentation de la contrainte du filtre
    */
  override def constraintsRepr: String = {
    s"airport.countryName in ${this.countryNames}"
  }
}
