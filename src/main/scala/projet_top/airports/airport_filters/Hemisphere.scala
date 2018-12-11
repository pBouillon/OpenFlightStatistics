package projet_top.airports.airport_filters
import projet_top.airports.Airport

sealed trait HemisphereChoice
case object North extends HemisphereChoice
case object South extends HemisphereChoice

case class Hemisphere(choice: HemisphereChoice) extends AirportFilter {
  /**
    * Teste si l'aéroport est sur l'hemisphère choisit
    *
    * @param candidate objet Airport à tester
    * @return true ssi l'aéroport est sur le bon hémisphère, false sinon
    */
  override def accepts(candidate: Airport): Boolean = choice match {
      // retient les aeroports au nord
    case North => candidate.latitude >= 0
      // retient les aeroports au sud
    case South => candidate.latitude <= 0
  }
}


