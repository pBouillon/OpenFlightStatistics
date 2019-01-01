package projet_top.projection

import java.awt.image.BufferedImage
import java.awt.{Color, Font, Graphics2D}
import java.io.File

import javax.imageio.ImageIO
import projet_top.flight.PathLike
import projet_top.globe.HasCoordinates
import projet_top.projection.backmap_providers.BackmapProvider
import projet_top.projection.markers.Marker
import projet_top.projection.projectors.Projector

object MapCreator {
  val DefaultTextPos: OnMap = OnMap(50, 50)
  val DefaultFontSize: Int = 12
  val DefaultFontColor: Color = Color.black
}

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
  val image: BufferedImage = backmapProvider.provide(projector)(width)

  /**
    * Brush permettant de modifier l'image (ici, d'ajouter les marqueurs).
    */
  val brush: Graphics2D = this.image.createGraphics()

  private val fontCreator = (fontSize: Int) => { new Font(Font.MONOSPACED, Font.PLAIN, fontSize) }

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

  def plotPathLike(pathLike: PathLike)(fromMarker: Marker, toMarker: Marker, fontSize: Int = MapCreator.DefaultFontSize, fontColor: Color = MapCreator.DefaultFontColor, textPos: OnMap = MapCreator.DefaultTextPos): Unit = {
    this.plotObject(pathLike.fromAirport)(fromMarker)
    this.plotObject(pathLike.toAirport)(toMarker)
    this.brush.setColor(fontColor)
    this.brush.setFont(this.fontCreator(fontSize))
    val lineHeight = Math.round(1.5 * fontSize).toInt
    pathLike.toString.split("\n").zipWithIndex.foreach {
      case (line, i) => this.brush.drawString(line, textPos.x.toFloat, (textPos.y + i * lineHeight).toFloat)
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
