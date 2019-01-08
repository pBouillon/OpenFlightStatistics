package projet_top.cli

import projet_top.airport.AirportDatabase
import projet_top.airport.airport_filters.{CountryNames, Hemisphere, Northern, Southern}
import projet_top.globe.HasCoordinates
import projet_top.projection.markers._
import projet_top.projection.projectors.{EquiRectangularLat0Projector, EquiRectangularProjector, Projector}

object CliProjectionLogic {

  /**
    *
    * @return
    */
  def genBase(): AirportDatabase = {
    var baseUsed = Cli.base

    print(s"    Représenter tous les aéroports de tous les pays ?(${Option.Ok}/${Option.No}): ")
    if (scala.io.StdIn.readLine() == Option.Ok) {
      return baseUsed
    }

    // exclusion hémisphère
    print(s"    Exclure un hémisphère ?(${Option.Ok}/${Option.No}): ")
    if (scala.io.StdIn.readLine() == Option.Ok) {
      print(s"    Quel hémisphère exclure ?(Nord = ${Option.Ok}/Sud = ${Option.No}): ")
      if (scala.io.StdIn.readLine() == Option.Ok) {
        baseUsed = baseUsed.getSubset(Hemisphere(Southern))
      }
      else {
        baseUsed = baseUsed.getSubset(Hemisphere(Northern))
      }
    }

    print(s"    N'afficher que certains pays ?(${Option.Ok}/${Option.No}): ")
    if (scala.io.StdIn.readLine() == Option.Ok) {
      println("    Entrez les pays à afficher avec les même noms que dans la base, séparés par un espace:")
      print("    " + Data.prefix)
      val toAdd = scala.io.StdIn.readLine()
      baseUsed = baseUsed.getSubset(CountryNames(toAdd.split(" ").toList))
    }

    baseUsed
  }

  /**
    *
    */
  def genMarker(): Marker = {
    var marker: Marker = null
    var filling: Filling = null

    // marker's style
    print(s"    Utiliser un marqueur plein ?(${Option.Ok}/${Option.No}): ")
    if (scala.io.StdIn.readLine() == Option.Ok) {
      filling = Filled
    }
    else {
      filling = Outline(Projection.defaultMarkerOutlineThickness)
    }

    // marker's shape
    println(
      "    Quel type de marker voulez-vous ?\n" +
        "    - 1) Rectangulaire\n" +
        "    - 2) Rond\n" +
        "    - 3) Carré"
    )

    print("    " + Data.prefix)
    val userInput = scala.io.StdIn.readInt()

    if (userInput == 1) {
      //noinspection RedundantNewCaseClass
      marker = new Rectangle(Projection.defaultMarkerColor, filling)(Projection.defaultMarkerSize * 2, Projection.defaultMarkerSize)
    }
    else if (userInput == 2) {
      //noinspection RedundantNewCaseClass
      marker = new Round(Projection.defaultMarkerColor, filling)(Projection.defaultMarkerSize)
    }
    else if (userInput == 3) {
      //noinspection RedundantNewCaseClass
      marker = new Square(Projection.defaultMarkerColor, filling)(Projection.defaultMarkerSize)
    }

    marker
  }

  /**
    *
    */
  def genProjector(): Projector = {
    var projector: Projector = null

    println(
      "    Quel type de projection voulez-vous ?\n" +
        s"    - 1) Equirectangular\n" +
        s"    - 2) Equirectangular centré sur un aéroport"
    )

    print("    " + Data.prefix)
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
    *
    * @return
    */
  //noinspection RedundantBlock
  def genProjectionCenter(): HasCoordinates = {
    var projectionCenter: HasCoordinates = null

    print("    Id de l'aéroport à utiliser comme centre (0 pour ne pas en sélectionner): ")
    val airportId = scala.io.StdIn.readInt()

    if (airportId == 0) {
      projectionCenter = Projection.center
    }

    else {
      projectionCenter = Cli.base.getAirportById(airportId)
      println(s"    Aéorport choisi: ${projectionCenter}")
    }

    projectionCenter
  }

}
