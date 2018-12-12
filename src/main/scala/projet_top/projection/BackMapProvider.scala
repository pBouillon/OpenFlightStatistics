package projet_top.projection

/**
  * Fournisseur de carte de fond
  */
trait BackMapProvider {
  def provide(projector: Projector)
}
