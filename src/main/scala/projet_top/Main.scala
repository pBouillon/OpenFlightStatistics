package projet_top

object CliUtilsObject {
  val retries: Int = 1000

  val errMessage: String =
    """
      | Commande inconnue, entrer une commande valide !
    """.stripMargin

  val header: String =
    """
     |*****************************************
     |  Projet TOP - Manipulations d'openflight
     |
     |   Auteurs:
     |  - BAGREL Thomas
     |  - BOUILLON Pierre
     |  - THOUVENIN Axel
     |  - VOGT Florian
     |*****************************************
    """.stripMargin

  val helper: String =
    s"""
      |*****************************************
      |Commandes disponibles:
      |  - ${Option.Quest1}) Résolution de la question 1
      |  - ${Option.Quest2}) Résolution de la question 2
      |  - ${Option.Quest3}) Résolution de la question 3
      |  - ${Option.Quest4}) Résolution de la question 4
      |  - ${Option.Quest5}) Résolution de la question 5
      |
      |  - ${Option.Help}) Afficher l'aide
      |  - ${Option.Quit}) Quitter l'application
      |*****************************************
    """.stripMargin

  val prefix: String = "projet_top > "
}

object Option extends Enumeration {
  type Option = Int

  // basic operations
  val Quit    = 0
  val Help    = 6

  // subject-specific questions
  val Quest1  = 1
  val Quest2  = 2
  val Quest3  = 3
  val Quest4  = 4
  val Quest5  = 5
}

object Main {

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
        println(CliUtilsObject.helper)
      }
      case Option.Quit => {
        println("Fermeture de l'application ...")
        System.exit(0)
      }
      case _ => {
        println(CliUtilsObject.errMessage)
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
    println(CliUtilsObject.header)

    // affiche le menu des commandes
    println(CliUtilsObject.helper)

    var userInput = 0
    while (true) {
      // en attente de l'entrée utilisateur
      print(CliUtilsObject.prefix)
      userInput = scala.io.StdIn.readInt()

      // execution de la commande
      executeCommand(userInput)
    }
  }

  /**
    *
    * @param args options de lancement de l'application (inutilisées)
    */
  def main(args: Array[String]): Unit = {
    launchCli()
  }

}
