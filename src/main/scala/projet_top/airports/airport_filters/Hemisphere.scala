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
      // Si on recherche les aéroports au nord, on retient ceux au dessus de l'equateur
    case North => candidate.latitude >= 0
      // Si on recherche les aéroports au sud, on retient ceux en dessous de l'equateur
    case South => candidate.latitude <= 0
      // Sinon, c'est une erreur ou un cas non traité
    case _ => false
  }
}


