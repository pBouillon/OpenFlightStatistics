package projet_top.cli

object Cli {
  /**
    * Traitement et execution de la commande utilisateur
    *
    * @see Option
    *
    * @param input entrée utilisateur à traiter
    */
  //noinspection RedundantBlock
  def executeCommand(input: Int): Unit ={
    input match {
      case Option.Quest1 => {
        println("Question 1")
      }
      case Option.Quest2 => {
        println("Question 2")
      }
      case Option.Quest3 => {
        println("Question 3")
      }
      case Option.Quest4 => {
        println("Question 4")
      }
      case Option.Quest5 => {
        println("Question 5")
      }
      case Option.Help => {
        println(CliData.helper)
      }
      case Option.Quit => {
        println("Fermeture de l'application ...")
        System.exit(0)
      }
      case _ => {
        println(CliData.errMessage)
      }
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
