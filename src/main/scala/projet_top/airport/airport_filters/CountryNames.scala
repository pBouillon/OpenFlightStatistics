package projet_top.airport.airport_filters

import projet_top.airport.Airport

/**
  * Objet compagnon de la classe CountryNames. Contient les méthodes et champs statiques.
  */
object CountryNames {
  /**
    * Case constructeur supplémentaire pour CountryNames, qui accepte un nombre quelconque de nom de pays, sans avoir
    * besoin de les mettre dans une liste.
    * Exemple :
    *     val myFilter = CountryNames("Australia", "France")
    * @param countryNames les nom de pays dont on veut connaître les aéroports
    * @return le filtre correspondant
    */
  def apply(countryNames: String*): CountryNames = CountryNames(countryNames.toList)
}

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
    */
  def accepts(candidate: Airport): Boolean = {
    this.countryNames.contains(candidate.countryName)
  }

  /**
    * Retourne une représentation de la contrainte du filtre.
    * @return une représentation de la contrainte du filtre
    */
  override def constraintsRepr: String = {
    s"airport.countryName in ${this.countryNames}"
  }
}
