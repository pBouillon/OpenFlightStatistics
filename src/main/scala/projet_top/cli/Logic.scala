package projet_top.cli

import projet_top.airport.Airport
import projet_top.globe.Utils

object Logic {

  /**
    * Implémentation avec affichage de la question 1
    */
  def questionOne(): Unit = {
    var file = ""

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
      s"Les aéroports utilisés pour la distance seront ceux " +
        s"d'ID ${firstAirport.airportId} et ${secondAirport.airportId}"
    )

    println("Evaluation de la distance ...")
    val distance = Utils.distance(firstAirport, secondAirport)
    //noinspection RedundantBlock
    println(s"La distance entre ces deux aéroports est de: ${distance} km.\n")

    println("Génération de la DistanceMap ...")
    val matrice = Cli.base.getDistanceMap
    //noinspection SpellCheckingInspection,RedundantBlock
    println(s"DistanceMap résultante: \n${matrice}")

    println()
  }

  /**
    * Implémentation avec affichage de la question 3
    */
  def questionThree(): Unit = {
    println("    +-----------")
    println("    | Question 3: calcul des statistiques descriptives des aéroports\n")
    // TODO
  }

  /**
    * Implémentation avec affichage de la question 4
    */
  def questionFour(): Unit = {
    println("    +-----------")
    println("    | Question 4: calcul des statistiques descriptives d'un sous-ensemble d'aéroports\n")
    // TODO
  }

  /**
    * Implémentation avec affichage de la question 5
    */
  def questionFive(): Unit = {
    println("    +-----------")
    println("    | Question 5: calcul de la densité d'aéroports par rapport à la superficie d'un pays\n")
    // TODO
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
