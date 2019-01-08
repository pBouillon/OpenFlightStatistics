package projet_top.cli.utils

object Displayables {

  // message d'erreur par défaut lors de la saisie d'une mauvaise commande
  val errMessage: String =
  """
      | Commande inconnue, entrer une commande valide !
    """.stripMargin

  // en-tête affichée au démarrage
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

  // helper avec les principales commandes disponibles
  val helper: String =
  s"""
     |*****************************************
     |Commandes disponibles:
     |  - ${Options.Quest1}) Résolution de la question 1 - chargement d'un fichier CSV
     |  - ${Options.Quest2}) Résolution de la question 2 - calcul de distance entre deux aéroports et matrice des distances
     |  - ${Options.Quest3}) Résolution de la question 3 - calcul des statistiques descriptives des aéroports
     |  - ${Options.Quest4}) Résolution de la question 4 - calcul des statistiques descriptives d'un sous-ensemble d'aéroports
     |  - ${Options.Quest5}) Résolution de la question 5 - calcul de la densité d'aéroports par rapport à la superficie d'un pays
     |  - ${Options.Quest6}) Résolution de la question 6 - projections d'aéroports sur une carte
     |
     |  - ${Options.Help}) Afficher les commandes disponibles
     |  - ${Options.Quit}) Quitter l'application
     |*****************************************
    """.stripMargin

  // préfixe par défaut pour les entrées utilisateur
  val prefix: String = "projet_top > "
}
