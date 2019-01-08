package projet_top

import projet_top.cli.Cli


object Main {

  /**
    *
    */
  def launchCLI(): Unit = {
        Cli.launchCli()
    }

  /**
    * @param args options de lancement de l'application (inutilisées)
    */
  def main(args: Array[String]): Unit = {
    Main.launchCLI()
  }

}
