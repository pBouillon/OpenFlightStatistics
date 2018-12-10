package projet_top.airports.airport_filters

import projet_top.airports.Airport

/**
  * Filtre qui accepte seulement les aéroports situés dans la liste des pays choisie.
  * @param countryNames la liste des nom de pays dont on veut connaître les aéroports
  */
case class CountryNames(countryNames: List[String]) extends AirportFilter {
  def accepts(candidate: Airport): Boolean = {
    countryNames.contains(candidate.countryName)
  }
}
