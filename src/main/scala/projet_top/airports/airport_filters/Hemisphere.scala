package projet_top.airports.airport_filters
import projet_top.airports.Airport

sealed trait HemisphereChoice
case object North extends HemisphereChoice
case object South extends HemisphereChoice

case class Hemisphere(choice: HemisphereChoice) extends AirportFilter {
  /**
    * Retourne true ssi le filtre "conserve" le candidat passé en paramètre.
    *
    * @param candidate objet Airport à tester
    * @return true ssi le filtre "conserve" le candidat passé en paramètre
    */
  override def accepts(candidate: Airport): Boolean = {
    // TODO
    false
  }
}


