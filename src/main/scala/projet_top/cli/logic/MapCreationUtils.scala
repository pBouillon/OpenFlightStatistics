package projet_top.cli.logic

import projet_top.airport.AirportDatabase
import projet_top.airport.airport_filters.{CountryNames, Hemisphere, Northern, Southern}
import projet_top.cli.utils.{Displayables, MapCreation}
import projet_top.cli.{Cli, utils}
import projet_top.globe.HasCoordinates
import projet_top.projection.markers._
import projet_top.projection.projectors.{EquiRectangularLat0Projector, EquiRectangularProjector, Projector}

object MapCreationUtils {
  /**
    * Génère une AirportDatabase modulée selon les précisions de l'utilisateur
    *
    * Prends initialement la totalité de la base chargée
    * Y enleve un hémisphère si demandé
    * N'affiche que certains pays si spécifiés
    *
    * @return l'AirportDatabase avec les spécifications souhaitées
    */
  def genBase(): AirportDatabase = {
    var baseUsed = Cli.base

    print(s"    Représenter tous les aéroports de tous les pays ?(${utils.Options.Ok}/${utils.Options.No}): ")
    if (scala.io.StdIn.readLine() == utils.Options.Ok) {
      return baseUsed
    }

    // exclude hemisphere
    print(s"    Exclure un hémisphère ?(${utils.Options.Ok}/${utils.Options.No}): ")
    if (scala.io.StdIn.readLine() == utils.Options.Ok) {
      print(s"    Quel hémisphère exclure ?(Nord = ${utils.Options.Ok} / Sud = ${utils.Options.No}): ")
      if (scala.io.StdIn.readLine() == utils.Options.Ok) {
        baseUsed = baseUsed.getSubset(Hemisphere(Southern))
      }
      else {
        baseUsed = baseUsed.getSubset(Hemisphere(Northern))
      }
    }

    // sélection de certains pays
    print(s"    N'afficher que certains pays ?(${utils.Options.Ok}/${utils.Options.No}): ")
    if (scala.io.StdIn.readLine() == utils.Options.Ok) {
      println("    Entrez les pays à afficher avec les même noms que dans la base, séparés par un espace:")
      print("    " + Displayables.prefix)
      val toAdd = scala.io.StdIn.readLine()
      baseUsed = baseUsed.getSubset(CountryNames(toAdd.split(" ").toList))
    }

    baseUsed
  }

  /**
    * Génère un Marker à utiliser pour signaler les aéroports sur la carte
    *
    * Génère un marqueur selon sa forme et son remplissage
    * La couleur, la taille et l'épaisseur sont spécifiées par défaut
    *
    * @see projet_top.cli.utils.MapCreation
    *
    * @return le Marker généré
    */
  def genMarker(): Marker = {
    var marker: Marker = null
    var filling: Filling = null

    // marker's style
    print(s"    Utiliser un marqueur plein ?(${utils.Options.Ok}/${utils.Options.No}): ")
    if (scala.io.StdIn.readLine() == utils.Options.Ok) {
      filling = Filled
    }
    else {
      filling = Outline(MapCreation.defaultMarkerOutlineThickness)
    }

    // marker's shape
    println(
      "    Quel type de marker voulez-vous ?\n" +
        "    - 1) Rectangulaire\n" +
        "    - 2) Rond\n" +
        "    - 3) Carré"
    )

    print("    " + Displayables.prefix)
    val userInput = scala.io.StdIn.readInt()

    if (userInput == 1) {
      //noinspection RedundantNewCaseClass
      marker = new Rectangle(MapCreation.defaultMarkerColor, filling)(MapCreation.defaultMarkerSize * 2, MapCreation.defaultMarkerSize)
    }
    else if (userInput == 2) {
      //noinspection RedundantNewCaseClass
      marker = new Round(MapCreation.defaultMarkerColor, filling)(MapCreation.defaultMarkerSize)
    }
    else if (userInput == 3) {
      //noinspection RedundantNewCaseClass
      marker = new Square(MapCreation.defaultMarkerColor, filling)(MapCreation.defaultMarkerSize)
    }

    marker
  }

  /**
    * Génère un Projector pour la projection de carte
    *
    * Sélectionne le type de projection souhaitée
    * Sélectionne un aéroport sur lequel centrer la projection si souhaité
    *
    * @return
    */
  def genProjector(): Projector = {
    var projector: Projector = null

    println(
      "    Quel type de projection voulez-vous ?\n" +
        s"    - 1) Equirectangular\n" +
        s"    - 2) Equirectangular centré sur un aéroport"
    )

    print("    " + Displayables.prefix)
    val userInput = scala.io.StdIn.readInt()

    if (userInput == 1) {
      projector = new EquiRectangularLat0Projector(genProjectionCenter())
    }
    else if (userInput == 2) {
      projector = new EquiRectangularProjector(genProjectionCenter())
    }

    projector
  }

  /**
    * Génère HasCoordinates centre de projection
    *
    * Prends la valeur des coordonnées d'un aéroport d'après son id
    * ou le centre de la carte par défaut
    *
    * @return le HasCoordinates résultant
    */
  //noinspection RedundantBlock
  def genProjectionCenter(): HasCoordinates = {
    var projectionCenter: HasCoordinates = null

    print("    Id de l'aéroport à utiliser comme centre (0 pour ne pas en sélectionner): ")
    val airportId = scala.io.StdIn.readInt()

    if (airportId == 0) {
      projectionCenter = MapCreation.center
    }
    else {
      projectionCenter = Cli.base.getAirportById(airportId)
      println(s"    Aéorport choisi: ${projectionCenter}")
    }

    projectionCenter
  }
}
