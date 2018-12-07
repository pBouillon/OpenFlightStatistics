package projet_top.airports.airport_filters
import projet_top.airports.Airport

sealed trait HemisphereChoice
case object North extends HemisphereChoice
case object South extends HemisphereChoice

case class Hemisphere(choice: HemisphereChoice) extends AirportFilter {
  /**
    * Retourne true ssi l'aéroport est sur le bon hemisphère
    * Si l'aéroport est sur l'équateur ou n'est pas sur l'hemisphère choisit, retourne false
    *
    * @param candidate objet Airport à tester
    * @return true ssi l'aéroport est sur le bon hémisphère, false sinon
    */
  override def accepts(candidate: Airport): Boolean = choice match {
    case North && candidate.latitude > 0 | South && candidate.latitude < 0  => true
    case _ => false
  }
}


