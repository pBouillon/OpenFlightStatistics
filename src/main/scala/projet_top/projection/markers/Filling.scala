package projet_top.projection.markers

/**
  * Indique si un Marker est rempli complètement avec la couleur choisie, ou alors seulement son contour.
  */
sealed trait Filling

/**
  * Cas où le Marker est rempli complètement avec la couleur choisie
  */
case object Filled extends Filling

/**
  * Cas où le Marker est rempli uniquement sur le contour, avec une largeur de trait spécifiée.
  * @param width largeur du trait pour le contour
  */
case class Outline(width: Float) extends Filling
