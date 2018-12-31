package projet_top.projection.backmap_providers

import java.awt.image.BufferedImage
import java.net.URL

import javax.imageio.ImageIO
import projet_top.projection.projectors.{EquiRectangularProjector, EquiRectangularLat0Projector, Projector}
import projet_top.Utils.encodeBase64

/**
  * Fourni un fond de carte à partir de l'API D3BackMapProvider (https://github.com/tbagrel1/d3backmapprovider).
  * @param rawApiUrl URL de l'API sur laquelle effectuer les requêtes pour récupérer les fonds de carte
  * @param style (optionel) chaîne contenant le style CSS à appliquer sur le fond de carte
  * @param customMapJsonDataUrl (optionel) URL d'un fichier JSON à utiliser comme données GeoJSON pour construire le fond de carte
  */
//noinspection RedundantBlock
class D3BackMapProvider(rawApiUrl: String, style: Option[String] = None, customMapJsonDataUrl: Option[String] = None) extends BackmapProvider {
  /**
    * URL normalisée de l'API.
    */
  val apiUrl: String = if (rawApiUrl endsWith "/") { rawApiUrl } else { rawApiUrl + "/" }

  /**
    * Retourne un fond de carte pour le projecteur spécifié.
    * @param projector le projecteur qui sera utilisé
    * @param width largeur désirée pour la carte
    * @return un objet BufferedImage correspondant au fond de carte fourni
    */
  override def provide(projector: Projector)(width: Int): BufferedImage = {
    val requestPart = this.requestBuilder(
      width, Math.round(width / projector.ratioWidthHeight).toInt,
      projector.center.latitude, projector.center.longitude,
      projector)
    val urlObj = new URL(this.apiUrl + requestPart)
    ImageIO.read(urlObj)
  }

  /**
    * Construit les queryParams afin de fournir le fond de carte désiré.
    * @param width largeur du fond de carte en pixels
    * @param height hauteur du fond de carte en pixels
    * @param centerLatitude latitude du centre de projection
    * @param centerLongitude longitude du centre de projection
    * @param projector projecteur pour lequel fournir le fond de carte
    * @return la partie queryParams de l'URL qui retournera le fond de carte désiré
    */
  private def requestBuilder(width: Int, height: Int, centerLatitude: Double, centerLongitude: Double,
                             projector: Projector): String = {
    val projectionMethodStr = projector match {
      case _: EquiRectangularProjector | _: EquiRectangularLat0Projector => "equirectangular"
      case _ => throw new UnsupportedOperationException(s"Projector ${projector.name} cannot be used with this provider")
    }
    var request = s"?width=${width}&height=${height}&lat=${centerLatitude}&long=${centerLongitude}&proj=${projectionMethodStr}"
    if (this.style.isDefined) {
      val styleB64 = encodeBase64(this.style.get)
      request += s"&style=${styleB64}"
    }
    if (this.customMapJsonDataUrl.isDefined) {
      val customMapJsonDataUrlB64 = encodeBase64(this.customMapJsonDataUrl.get)
      request += s"&dataurl=${customMapJsonDataUrlB64}"
    }
    request
  }
}
