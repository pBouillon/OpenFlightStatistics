package projet_top.cli.logic

import java.io.File

import projet_top.cli.Cli.defaultCountriesSources
import MapCreationUtils.{genBase, genMarker, genProjector}
import projet_top.cli.utils.{Displayables, MapCreation}
import projet_top.cli.{Cli, utils}
import projet_top.country.CountryDatabase
import projet_top.globe.Utils
import projet_top.projection.MapCreator
import projet_top.projection.projectors.Projector

object QuestionResolution {
  /**
    * Implémentation avec affichage de la question 1
    */
  def questionOne(): Unit = {
    println("    +-----------")
    println("    | Question 1: affichage du contenu du fichier CSV\n")

    print(s"    Votre base contient ${Cli.base.toList.length} aéroport(s), les afficher ? (${utils.Options.Ok}/${utils.Options.No}): ")

    if (scala.io.StdIn.readLine() == utils.Options.Ok) {
      Cli.base.toList
        .sortBy(airport => airport.airportId)
        .foreach(airport => println(s"    - $airport"))
    }

    println()
  }

  /**
    * Implémentation avec affichage de la question 2
    */
  //noinspection RedundantBlock
  def questionTwo(): Unit = {
    println("    +-----------")
    println("    | Question 2: calcul de distance entre deux aéroports et matrice des distances\n")

    print("    ID du premier aéroport: ")
    val firstAirport = Cli.base.toList(scala.io.StdIn.readInt())

    print("    ID du second aéroport: ")
    val secondAirport = Cli.base.toList(scala.io.StdIn.readInt())

    println(
      s"    Les aéroports utilisés pour la distance seront:\n" +
      s"- ${firstAirport}\n- ${secondAirport}\n"
    )

    println("    Evaluation de la distance ...")
    val distance = Utils.distance(firstAirport, secondAirport)
    println("    Evaluation effectuée.\n")

    //noinspection RedundantBlock
    println(s"    La distance entre ces deux aéroports est de: ${distance} km.\n")

    println("    Génération de la DistanceMap ...")
    val matrice = Cli.base.getDistanceMap
    println("    Génération terminée ...\n")

    //noinspection SpellCheckingInspection,RedundantBlock
    println(s"    DistanceMap résultante: \n    - ${matrice}")

    println()
  }

  /**
    * Implémentation avec affichage de la question 3
    */
  //noinspection RedundantBlock
  def questionThree(): Unit = {
    println("    +-----------")
    println("    | Question 3: calcul des statistiques descriptives des aéroports\n")

    println("    Génération de la DistanceMap ...")
    val matrice = Cli.base.getDistanceMap
    //noinspection SpellCheckingInspection
    println("    Génération terminée.\n")

    println("    Calculs des propriétés ...")
    val distMin = matrice.minDistance
    val distMax = matrice.maxDistance
    val distAvg = matrice.avgDistance
    val distMed = matrice.medianDistance
    val distStd = matrice.stdDev
    println("    Calculs effectués.\n")

    println(s"    - Distance minimale: ${distMin}")
    println(s"    - Distance maximale: ${distMax}")
    println(s"    - Distance moyenne: ${distAvg}")
    println(s"    - Distance médiane: ${distMed}")
    println(s"    - Ecart-type: ${distStd}")

    println()
  }

  /**
    * Implémentation avec affichage de la question 4
    */
  //noinspection RedundantBlock
  def questionFour(): Unit = {
    println("    +-----------")
    println("    | Question 4: calcul des statistiques descriptives d'un sous-ensemble d'aéroports\n")

    val generatedBase = genBase()

    print(
      s"    Votre base réduite contient ${generatedBase.toList.length} aéroport(s), " +
      s"les afficher ? (${utils.Options.Ok}/${utils.Options.No}): "
    )

    if (scala.io.StdIn.readLine() == utils.Options.Ok) {
      generatedBase.toList
        .sortBy(airport => airport.airportId)
        .foreach(airport => println(s"    - $airport"))
    }

    println()

    println(s"    DistanceMap de la base réduite: ${generatedBase.getDistanceMap}")

    println()
  }

  /**
    * Implémentation avec affichage de la question 5
    */
  //noinspection RedundantBlock
  def questionFive(): Unit = {
    println("    +-----------")
    println("    | Question 5: calcul de la densité d'aéroports par rapport à la superficie d'un pays\n")

    var countryBase: CountryDatabase = null

    print(s"    Voulez vous charger le fichier de pays par défaut ? (${utils.Options.Ok}/${utils.Options.No}): ")
    if (scala.io.StdIn.readLine() == utils.Options.Ok) {
      println("    Chargement ...")
      countryBase = CountryDatabase.loadFromCSV(new File(defaultCountriesSources))
    }
    else {
      print("    Entrer le chemin vers le fichier CSV: ")
      val file = scala.io.StdIn.readLine()

      println("    Chargement ...")
      countryBase = CountryDatabase.loadFromCSV(new File(file))
    }
    println("    Chargement effectué !\n")

    println("    Entrez le nom du pays (tel que présent en base) sur lequel faire ces statistiques")
    print("    " + Displayables.prefix)
    val countryName = scala.io.StdIn.readLine()

    println()

    println("    Calcul de densité ...")
    val density = Cli.base.getDensityIn(countryBase.getCountryByName(countryName), _.inhabitants)
    println("    Calcul effectué.\n")

    println(s"    Densité pour le pays choisi (${countryName}): ${density} aéoroport(s)/km²")

    println()
  }

  /**
    * Implémentation avec affichage de la question 6
    */
  //noinspection RedundantBlock
  def questionSix(): Unit = {
    println("    +-----------")
    println("    | Question 6: projections d'aéroports sur une carte\n")

    // image size
    print(
    s"    Voulez vous utiliser la largeur par défaut (${MapCreation.defaultWidth} px) ? (${utils.Options.Ok}/${utils.Options.No}): "
    )

    var imageWidth = 0
    if (scala.io.StdIn.readLine() == utils.Options.Ok) {
      imageWidth = MapCreation.defaultWidth
    }
    else {
      print(s"    Taille souhaitée entre ${MapCreation.minWidth} et ${MapCreation.maxWidth} (en px): ")
      imageWidth = scala.io.StdIn.readInt()
      if (imageWidth <= MapCreation.minWidth || imageWidth > MapCreation.maxWidth) {
        println(s"    Taille invalide, utilisation de la taille par défaut (${MapCreation.defaultWidth} px)")
        imageWidth = MapCreation.defaultWidth
      }
    }

    println()

    // projector
    val projector: Projector = genProjector()

    if (projector == null) {
      println("Type de projection inconnue ...\n")
      return
    }

    println()

    // marker
    val marker = genMarker()

    if (marker == null) {
      println("Type de marker inconnu ...\n")
      return
    }

    println()

    // map creation
    val mapCreator = new MapCreator(projector, MapCreation.defaultBackmapProvider)(imageWidth)

    val baseUsed = genBase()
    mapCreator.plotAll(baseUsed)(marker)

    println()

    // saving
    print(s"    Entrer le nom du fichier destination: ")
    val filename = scala.io.StdIn.readLine()
    mapCreator.saveToFile(new File(filename + ".png"))
    println(s"    Carte générée dans ${filename}.png")

    println()
  }
}
