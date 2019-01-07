package projet_top.cli

import java.io.File

import projet_top.airport.AirportDatabase

object Logic {

  /**
    * Implémentation avec affichage de la question 1
    */
  def questionOne(): Unit = {
    var file = ""

    println("\t+-----------")
    println("\t| Question 1: chargement d'un fichier CSV\n")

    print("\tEntrer le chemin vers le fichier CSV: ")
    file = scala.io.StdIn.readLine()

    println("\tChargement ...")
    val maBase = AirportDatabase.loadFromCSV(new File(file))
    println("\tChargement effectué !")

    print(s"\tVotre fichier contient ${maBase.toList.length} aéroport(s), les afficher ? (Y/N): ")

    if (scala.io.StdIn.readLine() == "Y") {
      maBase.toList
        .sortBy(airport => airport.airportId)
        .foreach(airport => println(s"\t- $airport"))
    }

    println()
  }

  /**
    * Implémentation avec affichage de la question 2
    */
  def questionTwo(): Unit = {
    println("\t+-----------")
    println("\t| Question 2: calcul de distance entre deux aéroports et matrice des distances\n")
    // TODO
  }

  /**
    * Implémentation avec affichage de la question 3
    */
  def questionThree(): Unit = {
    println("\t+-----------")
    println("\t| Question 3: calcul des statistiques descriptives des aéroports\n")
    // TODO
  }

  /**
    * Implémentation avec affichage de la question 4
    */
  def questionFour(): Unit = {
    println("\t+-----------")
    println("\t| Question 4: calcul des statistiques descriptives d'un sous-ensemble d'aéroports\n")
    // TODO
  }

  /**
    * Implémentation avec affichage de la question 5
    */
  def questionFive(): Unit = {
    println("\t+-----------")
    println("\t| Question 5: calcul de la densité d'aéroports par rapport à la superficie d'un pays\n")
    // TODO
  }

  /**
    * Implémentation avec affichage de la question 6
    */
  def questionSix(): Unit = {
    println("\t+-----------")
    println("\t| Question 6: carte des aéroports\n")
    // TODO
  }

  /**
    * Implémentation avec affichage de la question 7
    */
  def questionSeven(): Unit = {
    println("\t+-----------")
    println("\t| Question 7: carte des aéroports centrée sur l'un d'entre eux\n")
    // TODO
  }
}
