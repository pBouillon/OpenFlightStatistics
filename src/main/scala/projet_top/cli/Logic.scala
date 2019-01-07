package projet_top.cli

import java.io.File

import projet_top.airport.AirportDatabase
import projet_top.airport.airport_filters.{CountryNames, Hemisphere, Northern}
import projet_top.cli.Cli.{base, defaultCountriesSources}
import projet_top.country.CountryDatabase
import projet_top.globe.Utils

object Logic {

  /**
    * Implémentation avec affichage de la question 1
    */
  def questionOne(): Unit = {
    println("    +-----------")
    println("    | Question 1: chargement d'un fichier CSV\n")

    print(s"    Votre base contient ${Cli.base.toList.length} aéroport(s), les afficher ? (${Option.Ok}/${Option.No}): ")

    if (scala.io.StdIn.readLine() == Option.Ok) {
      Cli.base.toList
        .sortBy(airport => airport.airportId)
        .foreach(airport => println(s"    - $airport"))
    }

    println()
  }

  /**
    * Implémentation avec affichage de la question 2
    */
  def questionTwo(): Unit = {
    println("    +-----------")
    println("    | Question 2: calcul de distance entre deux aéroports et matrice des distances\n")

    //noinspection ZeroIndexToHead
    val firstAirport = Cli.base.toList(0)
    val secondAirport = Cli.base.toList(1)

    println(
      s"    Les aéroports utilisés pour la distance seront ceux " +
        s"d'ID ${firstAirport.airportId} et ${secondAirport.airportId}"
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

    //noinspection ZeroIndexToHead
    val firstAirport = Cli.base.toList(0)
    val secondAirport = Cli.base.toList(1)

    println(
      s"Les aéroports utilisés pour la distance seront ceux " +
        s"d'ID ${firstAirport.airportId} et ${secondAirport.airportId}"
    )

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

    println(
      "    Les filtres utilisés sont: \n" +
      "    - Dans l'hémisphère Sud\n" +
      "    - Ou au Canada\n"
    )

    println("    Calcul de la matrice réduite ...")
    val subset = Cli.base.getSubset(CountryNames(List("Canada")) || Hemisphere(Northern)).getDistanceMap
    println("    Calcul effectué.\n")

    println(s"   Matrice réduite:\n    - ${subset}\n")

    println(s"    Calcul de la distance minimale sur cette matrice: ${subset.minDistance}")

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

    print(s"    Voulez vous charger le fichier de pays par défaut ? (${Option.Ok}/${Option.No}): ")
    if (scala.io.StdIn.readLine() == Option.Ok) {
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

    println("    Pour ces tests, nous calculerons la densité par rapport pour le Canada.\n")

    println("    Calcul de densité ...")
    val density = Cli.base.getDensityIn(countryBase.getCountryByName("Canada"), _.inhabitants)
    println("    Calcul effectué.\n")

    println(s"Densité d'aéroports par rapport à la superficie au Canada: ${density}")

    println()
  }

  /**
    * Implémentation avec affichage de la question 6
    */
  def questionSix(): Unit = {
    println("    +-----------")
    println("    | Question 6: carte des aéroports\n")
    // TODO
  }

  /**
    * Implémentation avec affichage de la question 7
    */
  def questionSeven(): Unit = {
    println("    +-----------")
    println("    | Question 7: carte des aéroports centrée sur l'un d'entre eux\n")
    // TODO
  }
}
