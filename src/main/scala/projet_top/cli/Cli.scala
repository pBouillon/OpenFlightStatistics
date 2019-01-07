package projet_top.cli

import java.io.File

import projet_top.airport.AirportDatabase

object Cli {

  val default_sources = "resources/airports.csv"

  var base: AirportDatabase = _

  /**
    *
    */
  def load_csv(): Unit = {
    println(s"Voulez vous charger le fichier d'aéroports par défaut ? (${Option.Ok}/${Option.No}): ")

    if (scala.io.StdIn.readLine() == Option.Ok) {
      base = AirportDatabase.loadFromCSV(new File(default_sources))
    }
    else {
      print("Entrer le chemin vers le fichier CSV: ")
      val file = scala.io.StdIn.readLine()

      println("Chargement ...")
      base = AirportDatabase.loadFromCSV(new File(file))
    }
    println("Chargement effectué !")
  }

  /**
    * Traitement et execution de la commande utilisateur
    *
    * @see Option
    *
    * @param input entrée utilisateur à traiter
    */
  def executeCommand(input: Int): Unit ={
    input match {
      case Option.Quest1 => Logic.questionOne()
      case Option.Quest2 => Logic.questionTwo()
      case Option.Quest3 => Logic.questionThree()
      case Option.Quest4 => Logic.questionFour()
      case Option.Quest5 => Logic.questionFive()
      case Option.Quest6 => Logic.questionSix()
      case Option.Quest7 => Logic.questionSeven()
      case Option.Help =>
        println(CliData.helper)
      case Option.Quit =>
        println("Fermeture de l'application ...")
        System.exit(0)
      case _ => println(CliData.errMessage)
    }
  }

  /**
    * Lancement du CLI
    *
    * Affiche le message d'accueil
    * Ensuite affiche les commandes disponibles
    * Puis affiche le menu interactif jusqu'à ce qu'il soit coupé par l'utilisateur
    */
  def launchCli(): Unit = {
    // affiche l'en tête du projet
    println(CliData.header)

    // charge une base
    load_csv()

    // affiche le menu des commandes
    println(CliData.helper)

    var userInput = 0
    while (true) {
      // en attente de l'entrée utilisateur
      print(CliData.prefix)
      userInput = scala.io.StdIn.readInt()

      // execution de la commande
      executeCommand(userInput)
    }
  }
}
