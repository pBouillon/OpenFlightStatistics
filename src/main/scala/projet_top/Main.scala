package projet_top

import projet_top.cli.Cli

object Main {

  /**
    * @param args options de lancement de l'application (inutilisées)
    */
  def main(args: Array[String]): Unit = {
    Cli.launchCli()
  }

}
