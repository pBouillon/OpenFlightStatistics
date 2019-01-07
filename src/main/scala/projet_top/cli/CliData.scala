package projet_top.cli

object CliData {
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
       |  - ${Option.Quest6}) Résolution de la question 6
       |  - ${Option.Quest7}) Résolution de la question 7
       |
       |  - ${Option.Quit}) Quitter l'application
       |*****************************************
    """.stripMargin

  val prefix: String = "projet_top > "
}
