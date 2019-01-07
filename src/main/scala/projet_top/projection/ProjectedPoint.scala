package projet_top.projection

/**
  * Représente la position après projection d'un objet. Il peut être soit en dehors de la carte
  * (pour certains types de projection), soit sur la carte à une position (x, y).
  */
sealed abstract class ProjectedPoint

/**
  * Point projeté en dehors de la carte (peut se passer avec Mercator par exemple);
  */
case object OutOfMap extends ProjectedPoint {
  /**
    * Retourne une représentation textuelle du point projeté.
    * @return une représentation textuelle du point projeté
    */
  override def toString: String = {
    "ProjectedPoint[<out of map>]"
  }
}

/**
  * Point projeté sur la carte
  * @param x coordonnée x en pixels
  * @param y coordonnée y en pixels
  */
case class OnMap(x: Double, y: Double) extends ProjectedPoint {
  /**
    * Retourne une représentation textuelle du point projeté.
    * @return une représentation textuelle du point projeté
    */
  override def toString: String = {
    s"ProjectedPoint[<x: ${this.x}, y: ${this.y}>]"
  }
}

