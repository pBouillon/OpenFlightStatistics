package projet_top.cli

import java.io.File

import projet_top.airport.AirportDatabase
import projet_top.cli.logic.QuestionResolution
import projet_top.cli.utils.Displayables

object Cli {

  /**
    * chemin vers le fichier .csv par défaut des aéroports
    */
  val defaultAirportsSources = "resources/lightAirports.csv"

  /**
    * chemin vers le fichier .csv par défaut des pays
    */
  val defaultCountriesSources = "resources/countries.csv"

  /**
    * AirportDatabase utilisée par le CLI
    */
  var base: AirportDatabase = _

  /**
    * Charge un csv par défaut ou spécifié par l'utilisateur si demandé
    */
  def load_csv(): Unit = {
    print(s"Voulez vous charger le fichier d'aéroports par défaut ? (${utils.Options.Ok}/${utils.Options.No}): ")

    if (scala.io.StdIn.readLine() == utils.Options.Ok) {
      println("Chargement ...")
      base = AirportDatabase.loadFromCSV(new File(defaultAirportsSources))
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
  def executeCommand(input: Int): Unit = {
    try {
      input match {
        case utils.Options.Quest1 => QuestionResolution.questionOne()
        case utils.Options.Quest2 => QuestionResolution.questionTwo()
        case utils.Options.Quest3 => QuestionResolution.questionThree()
        case utils.Options.Quest4 => QuestionResolution.questionFour()
        case utils.Options.Quest5 => QuestionResolution.questionFive()
        case utils.Options.Quest6 => QuestionResolution.questionSix()
        case utils.Options.Help =>
          println(Displayables.helper)
        case utils.Options.Quit =>
          println("Fermeture de l'application ...")
          System.exit(0)
        case _ => println(Displayables.errMessage)
      }
    } catch {
      case _: java.lang.NumberFormatException => println("\nMauvais format d'entrée - annulation ...\n")
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
    println(Displayables.header)

    // charge une base
    load_csv()

    // affiche le menu des commandes
    println(Displayables.helper)

    var userInput = 0
    while (true) {
      // en attente de l'entrée utilisateur
      print(Displayables.prefix)

      try {
        userInput = scala.io.StdIn.readInt()
      } catch {
        case _: java.lang.NumberFormatException => userInput = -1
      }

      // execution de la commande
      executeCommand(userInput)
    }
  }
}
