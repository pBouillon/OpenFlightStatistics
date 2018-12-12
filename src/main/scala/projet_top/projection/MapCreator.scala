package projet_top.projection

import java.awt.Image
import projet_top.projection
/**
  * Créateur de la carte avec les aéroports projetés
  *
  * @param projector Objet qui contient la projection mathématique
  * @param backmapProvider Objet qui fournit le fond de carte par rapport à la projection
  */
class MapCreator(projector: Projector, backmapProvider: BackMapProvider) {

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
