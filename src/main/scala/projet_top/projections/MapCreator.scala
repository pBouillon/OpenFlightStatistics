package projet_top.projections

import java.awt.Image

import projet_top.projections
import projet_top.projections.backmap_providers.BackmapProvider
import projet_top.projections.projectors.Projector
/**
  * Créateur de la carte avec les aéroports projetés
  *
  * @param projector Objet qui contient la projections mathématique
  * @param backmapProvider Objet qui fournit le fond de carte par rapport à la projections
  */
class MapCreator(projector: Projector, backmapProvider: BackmapProvider) {

  /**
    * Ajoute un aéroport sur la carte
    *
    * @param toAdd objet avec des coordonnées
    */
  def addObject(toAdd: HasCoordinates): Unit = {
    // TODO
  }

  /**
    * Retourne une image sous format <A Definir> avec les aéroports et le fond de carte
    *
    * @return Image de la carte
    */
  def saveFinalPNG() : Image = {
    // TODO
    null
  }
}
