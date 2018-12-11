package projet_top.airports.airport_filters
import projet_top.airports.Airport

sealed trait HemisphereChoice
case object North extends HemisphereChoice
case object South extends HemisphereChoice

case class Hemisphere(choice: HemisphereChoice) extends AirportFilter {
  /**
    * Ne conserve que les aéroports situés dans l'hémisphère choisi, équateur compris.
    *
    * @param candidate objet Airport à tester
    * @return true ssi l'aéroport est sur le bon hémisphère, false sinon
    */
  override def accepts(candidate: Airport): Boolean = this.choice match {
      // retient les aéroports au nord
    case North => candidate.latitude >= 0
      // retient les aéroports au sud
    case South => candidate.latitude <= 0
  }
}


