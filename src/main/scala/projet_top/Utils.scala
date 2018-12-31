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
}
