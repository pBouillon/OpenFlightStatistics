package projet_top.projection

import java.awt.Graphics2D
import java.awt.image.BufferedImage
import java.io.File

import javax.imageio.ImageIO
import projet_top.airport.AirportDatabase
import projet_top.globe.HasCoordinates
import projet_top.projection.backmap_providers.BackmapProvider
import projet_top.projection.markers.Marker
import projet_top.projection.projectors.Projector

/**
  * Builder de la carte affichant les aéroports.
  *
  * @param projector projecteur utilisé (définit complétement la projection)
  * @param backmapProvider fournisseur du fond de carte pour la projection choisie
  * @param width largeur pour la carte finale
  */
class MapCreator(projector: Projector, backmapProvider: BackmapProvider)(width: Int) {

  /**
    * Image en train d'être modifiée qui représente la carte + les marqueurs tracés.
    */
  private val image: BufferedImage = backmapProvider.provide(projector)(width)

  /**
    * Brush permettant de modifier l'image (ici, d'ajouter les marqueurs).
    */
  private val brush: Graphics2D = this.image.createGraphics()

  /**
    * Place un marqueur sur la carte à la position de l'objet.
    * @param obj Objet que l'on veut représenter sur la carte
    * @param marker marqueur à utiliser pour représenter l'objet
    */
  def plotObject(obj: HasCoordinates)(marker: Marker): Unit = {
    val projected = this.projector.projects(obj)(this.width)
    projected match {
      case OutOfMap => println(s"[WARNING] Object ${obj} cannot be plotted on the map with projector ${this.projector} !")
      case OnMap(x, y) => marker.putAt(this.brush)(x, y)
    }
  }

  /**
    * Place un marqueur sur la carte pour chaque aéroport de la database choisie.
    * @param airportDatabase la database à utiliser
    * @param marker le marqueur à placer pour chaque aéroport
    */
  def plotAll(airportDatabase: AirportDatabase)(marker: Marker): Unit = {
    airportDatabase.toList.foreach {
      airport => this.plotObject(airport)(marker)
    }
  }

  /**
    * Finalise l'image et la sauvegarde dans le fichier spécifié.
    * @param outputFile fichier dans lequel sauvegarder l'image
    */
  def saveToFile(outputFile: File): Unit = {
    this.brush.dispose()
    ImageIO.write(this.image, "png", outputFile)
  }
}
