package projet_top.projection.backmap_providers
import java.awt.image.BufferedImage
import java.net.URL

import javax.imageio.ImageIO
import projet_top.Utils.encodeBase64
import projet_top.projection.projection_types.{EquiRectangular, Projection}
import projet_top.projection.projectors.Projector

//noinspection RedundantBlock
class D3BackMapProvider(rawApiUrl: String, style: Option[String] = None, customMapJsonDataUrl: Option[String] = None) extends BackmapProvider {
  private[this] val apiUrl = if (rawApiUrl endsWith "/") { rawApiUrl } else { rawApiUrl + "/" }

  override def provide(projector: Projector)(width: Int): BufferedImage = {
    val requestPart = this.requestBuilder(
      width, Math.round(width / projector.projection.ratioWidthHeight).toInt,
      projector.center.latitude, projector.center.longitude,
      projector.projection, this.style, this.customMapJsonDataUrl
    )
    val urlObj = new URL(this.apiUrl + requestPart)
    ImageIO.read(urlObj)
  }

  private def requestBuilder(width: Int, height: Int, centerLatitude: Double, centerLongitude: Double, projection: Projection, style: Option[String], customMapJsonDataUrl: Option[String]): String = {
    val projectionMethodStr = projection match {
      case EquiRectangular => "equirectangular"
      case _ => throw new UnsupportedOperationException(s"Projection ${projection.name} cannot be used with this provider")
    }
    var request = s"?width=${width}&height=${height}&lat=${centerLatitude}&long=${centerLongitude}&proj=${projectionMethodStr}"
    if (style.isDefined) {
      val styleB64 = encodeBase64(style.get)
      request += s"&style=${styleB64}"
    }
    if (customMapJsonDataUrl.isDefined) {
      val customMapJsonDataUrlB64 = encodeBase64(customMapJsonDataUrl.get)
      request += s"&dataurl=${customMapJsonDataUrlB64}"
    }
    request
  }
}
