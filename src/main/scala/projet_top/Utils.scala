package projet_top

import java.util.Base64

/**
  * Contient les méthodes statiques utilitaires générales
  */
object Utils {
  /**
    * Retourne la version encodée en base64 de la chaîne (ne contenant que des caractères ASCII) spécifiée.
    * @param data chaîne à encoder (ne doit contenir que des caractères ASCII)
    * @return la version encodée en base64 de la chaîne
    */
  def encodeBase64(data: String): String = {
    Base64.getEncoder.encodeToString(data.getBytes("ASCII"))
  }

  /**
    * Préfixe chaque ligne de sourceString par le préfixe choisi, et retourne
    * la chaîne résultat.
    * @param sourceString chaîne dont on veut préfixer chaque ligne
    * @param prefix préfixe pour chaque début de ligne de sourceString
    * @return le résultat du préfixage
    */
  def prefixLinesWith(sourceString: String, prefix: String): String = {
    sourceString.split("\\n").map(line => { if (line.length > 0) { prefix + line } else line }).mkString("\n")
  }
}
