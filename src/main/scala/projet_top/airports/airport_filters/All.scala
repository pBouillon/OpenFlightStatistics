package projet_top.airports.airport_filters
import projet_top.airports.Airport

/**
  * Filtre par défaut qui accepte tous les aéroports.
  */
object All extends AirportFilter {
  /**
    * Retourne true ssi le filtre "conserve" le candidat passé en paramètre.
    *
    * @param candidate objet Airport à tester
    * @return true ssi le filtre "conserve" le candidat passé en paramètre
    */
  override def accepts(candidate: Airport): Boolean = true
}
