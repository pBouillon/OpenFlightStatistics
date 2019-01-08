package projet_top.cli

import java.awt.Color

import projet_top.globe.Point
import projet_top.projection.backmap_providers.{BackmapProvider, D3BackMapProvider}

/**
  *
  */
object Data {
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
       |
       |  - ${Option.Help}) Afficher les commandes disponibles
       |  - ${Option.Quit}) Quitter l'application
       |*****************************************
    """.stripMargin

  val prefix: String = "projet_top > "
}

/**
  *
  */
object Projection {
  // map privoder
  val defaultBackmapProvider: BackmapProvider =
    new D3BackMapProvider("http://d3backmapprovider.westeurope.azurecontainer.io:8080/")

  // marker
  val defaultMarkerColor: Color = Color.blue
  val defaultMarkerOutlineThickness: Int = 2
  val defaultMarkerSize: Int = 5

  // image size
  val defaultWidth: Int = 4000
  val minWidth: Int = 100
  val maxWidth: Int = 8000

  // globe center
  val center: Point = Point(.0, .0)
}