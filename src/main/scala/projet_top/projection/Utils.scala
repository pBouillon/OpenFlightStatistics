package projet_top.projection

import projet_top.globe.{HasCoordinates, Point}

/**
  * Contient des méthodes statiques utilitaires pour la projection
  */
object Utils {

  /**
    * Contient toutes les fonctions de trigonométries pour travailler avec des
    * degrées (au lieu des radians par défaut).
    */
  object Degrees {
    /**
      * Cosinus (degrés).
      * @param x angle en degrés
      * @return cos(x)
      */
    def cos(x: Double): Double = math.cos(x.toRadians)

    /**
      * Sinus (degrés).
      * @param x angle en degrés
      * @return sin(x)
      */
    def sin(x: Double): Double = math.sin(x.toRadians)

    /**
      * Tangente (degrés).
      * @param x angle en degrés
      * @return tan(x)
      */
    def tan(x: Double): Double = math.tan(x.toRadians)

    /**
      * Arccos (degrés).
      * @param x valeur du cos
      * @return arccos(x) en degrés
      */
    def acos(x: Double): Double = math.acos(x).toDegrees

    /**
      * Arcsin (degrés).
      * @param x valeur du sin
      * @return arcsin(x) en degrés
      */
    def asin(x: Double): Double = math.asin(x).toDegrees

    /**
      * Arctan (degrés).
      * @param x valeur de tan
      * @return arctan(x) en degrés
      */
    def atan(x: Double): Double = math.atan(x).toDegrees

    /**
      * Arctan2 (degrés).
      * @param x valeur x
      * @param y valeur y
      * @return arctan(x, y) en degrés
      */
    def atan2(x: Double, y: Double): Double = math.atan2(x, y).toDegrees
  }

  /**
    * Retourne la latitude normalisée (ie dans la plage [-90°, 90°]).
    * @param originalLatitude latitude en degrés à normaliser
    * @return la latitude normalisée
    */
  def normLatitude(originalLatitude: Double): Double = {
    var latitude = originalLatitude
    while (latitude < -90.0) { latitude += 180.0 }
    while (latitude >= 90.0) { latitude -= 180.0 }
    latitude
  }

  /**
    * Retourne la longitude normalisée (ie dans la plage [-180°, 180°]).
    * @param originalLongitude longitude en degrés à normaliser
    * @return la longitude normalisée
    */
  def normLongitude(originalLongitude: Double): Double = {
    var longitude = originalLongitude
    while (longitude < -180.0) { longitude += 360.0 }
    while (longitude >= 180.0) { longitude -= 360.0 }
    longitude
  }

  // https://github.com/d3/d3-geo/blob/master/src/rotation.js

  /**
    * Calcule les coordonnées d'un objet après rotation de la longitude choisie.
    * (voir https://github.com/d3/d3-geo/blob/master/src/rotation.js)
    * @param rotLongitude delta-longitude de rotation
    * @param obj objet dont on veut connaître les coordonnées après rotation
    * @return les coordonnées après rotation de l'objet
    */
  private def rotationLongitude(rotLongitude: Double)(obj: HasCoordinates): Point = {
    Point(obj.latitude, normLongitude(obj.longitude + rotLongitude))
  }

  /**
    * Calcule les coordonnées d'un objet après rotation de la latitude choisie.
    * (voir https://github.com/d3/d3-geo/blob/master/src/rotation.js)
    * @param rotLatitude delta-latitude de rotation
    * @param obj objet dont on veut connaître les coordonnées après rotation
    * @return les coordonnées après rotation de l'objet
    */
  private def rotationLatitude(rotLatitude: Double)(obj: HasCoordinates): Point = {
    // on travaille en degrés partout
    import Degrees._

    val (x, y, z) = (cos(obj.longitude) * cos(obj.latitude), sin(obj.longitude) * cos(obj.latitude), sin(obj.latitude))
    val k = z * cos(rotLatitude) + x * sin(rotLatitude)
    Point(normLatitude(asin(k)), normLongitude(atan2(y, x * cos(rotLatitude) - z * sin(rotLatitude))))
  }

  /**
    * Créé une fonction qui calcule les coordonnées d'un objet après une rotation de longitude puis latitude choisie.
    * @param rotLatitude delta-latitude de rotation
    * @param rotLongitude delta-longitude de rotation
    * @return la fonction de calcul de coordonnées
    */
  def rotator(rotLatitude: Double, rotLongitude: Double): Function[HasCoordinates, Point] = {
    val rotLatFunc = rotationLatitude(rotLatitude) _
    val rotLongFunc = rotationLongitude(rotLongitude) _

    (obj: HasCoordinates) => rotLatFunc(rotLongFunc(obj))
  }

  /**
    * Convertit une forme rectangulaire donnée avec les paramètres {coordonnées du centre, largeur, hauteur}
    * en une forme avec les paramètres {coordonnées du point haut-gauche, largeur, hauteur}.
    * @param xCenter coordonnée x du centre
    * @param yCenter coordonnée y du centre
    * @param width largeur
    * @param height hauteur
    * @return {coordonnées du point haut-gauche, largeur, hauteur}
    */
  def centerWHToULWH(xCenter: Double, yCenter: Double, width: Double, height: Double): (Double, Double, Double, Double) = {
    val xUpperLeft = xCenter - width / 2.0
    val yUpperLeft = yCenter - height / 2.0
    (xUpperLeft, yUpperLeft, width, height)
  }

  /**
    * Convertit une forme carrée donnée avec les paramètres {coordonnées du centre, taille du côté}
    * en une forme avec les paramètres {coordonnées du point haut-gauche, largeur, hauteur}.
    * @param xCenter coordonnée x du centre
    * @param yCenter coordonnée y du centre
    * @param size taille du côté du carré
    * @return {coordonnées du point haut-gauche, largeur, hauteur}
    */
  def centerSizeToULWH(xCenter: Double, yCenter: Double, size: Double): (Double, Double, Double, Double) = {
    val xUpperLeft = xCenter - size / 2.0
    val yUpperLeft = yCenter - size / 2.0
    (xUpperLeft, yUpperLeft, size, size)
  }
}
