package projet_top.airport.airport_filters
import projet_top.airport.Airport

/**
  * Représente un hémisphère.
  */
sealed trait HemisphereChoice

/**
  * Hémisphère Nord.
  */
case object Northern extends HemisphereChoice {
  override def toString: String = "Northern"
}

/**
  * Hémisphère Sud.
  */
case object Southern extends HemisphereChoice {
  override def toString: String = "Southern"
}

case class Hemisphere(choice: HemisphereChoice) extends AirportFilter {
  /**
    * Ne conserve que les aéroports situés dans l'hémisphère choisi, équateur compris.
    *
    * @param candidate objet Airport à tester
    * @return true ssi l'aéroport est sur le bon hémisphère, false sinon
    */
  override def accepts(candidate: Airport): Boolean = this.choice match {
      // retient les aéroports au nord
    case Northern => candidate.latitude >= 0
      // retient les aéroports au sud
    case Southern => candidate.latitude <= 0
  }

  /**
    * Retourne une représentation de la contrainte du filtre.
    * @return une représentation de la contrainte du filtre
    */
  override def constraintsRepr: String = {
    s"airport in ${this.choice} hemisphere"
  }
}


