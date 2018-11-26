/**
  * Méthodes utiles lors des tests unitaires
  */
object TestUtils {
  /**
    * Retourne vrai ssi l'erreur relative entre 'found' et 'expected' est inférieure ou égale à la valeur 'allowedRatioError' choisie.
    * @param allowedRatioError l'erreur relative autorisée
    * @param found la valeur expérimentale
    * @param expected la valeur théorique ou attendue
    * @return vrai ssi l'erreur relative entre 'found' et 'expected' est inférieure ou égale à la valeur 'allowedRatioError' choisie.
    */
  def isErrorWithin(allowedRatioError: Double)(found: Double, expected: Double): Boolean = {
    if (found < 0) {
      throw new IllegalArgumentException("a negative value for 'found' is not allowed")
    }
    if (expected <= 0) {
      throw new IllegalArgumentException("a negative or zero value for 'expected' is not allowed")
    }
    (expected - found).abs / expected <= allowedRatioError
  }
}
