package projet_top.projection

import java.awt.Graphics2D
import java.awt.image.BufferedImage
import java.io.File

import javax.imageio.ImageIO
import projet_top.globe.HasCoordinates
import projet_top.projection.backmap_providers.BackmapProvider
import projet_top.projection.projectors.Projector

/**
  * Builder de la carte affichant les aéroports.
  *
  * @param projector projecteur utilisé (définit complétement la projection)
  * @param backmapProvider fournisseur du fond de carte pour la projection choisie
  * @param width largeur pour la carte finale
  */
case class MapCreator(projector: Projector, backmapProvider: BackmapProvider)(width: Int) {

  val image: BufferedImage = backmapProvider.provide(projector)(width)
  val brush: Graphics2D = this.image.createGraphics()

  def plotObject(obj: HasCoordinates)(marker: Marker): Unit = {
    val projected = this.projector.projects(obj)(this.width)
    projected match {
      case OutOfMap => Unit  // Do nothing
      case OnMap(x, y) => marker(this.brush)(x, y)
    }
  }

  def saveToFile(outputFile: File): Unit = {
    this.brush.dispose()
    ImageIO.write(this.image, "png", outputFile)
  }
}
