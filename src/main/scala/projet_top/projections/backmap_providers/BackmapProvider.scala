package projet_top.projections.backmap_providers

import java.awt.image.BufferedImage

import projet_top.projections.projectors.Projector

// http://otfried.org/scala/drawing.html

/**
  * Représente un fournisseur de fond de carte
  */
abstract class BackmapProvider {
  /**
    * Retourne un fond de carte pour le projecteur spécifié
    * @param projector le projecteur qui sera utilisé
    * @param width largeur désirée pour la carte
    * @param height hauteur désirée pour la carte
    * @return un objet BufferedImage correspondant au fond de carte fourni
    */
  def provide(projector: Projector)(width: Int, height: Int): BufferedImage
}
