package projet_top.cli.utils

import java.awt.Color

import projet_top.globe.Point
import projet_top.projection.backmap_providers.{BackmapProvider, D3BackmapProvider}

object MapCreation {
  // map provider
  val defaultBackmapProvider: BackmapProvider =
    new D3BackmapProvider("http://d3backmapprovider.westeurope.azurecontainer.io:8080/")

  // marker
  var defaultMarkerColorValue: String = "#D34747"
  val defaultMarkerColor: Color = new Color (
    Integer.valueOf( defaultMarkerColorValue.substring( 1, 3 ), 16 ),
    Integer.valueOf( defaultMarkerColorValue.substring( 3, 5 ), 16 ),
    Integer.valueOf( defaultMarkerColorValue.substring( 5, 7 ), 16 )
  )
  val defaultMarkerOutlineThickness: Int = 2
  val defaultMarkerSize: Int = 5

  // image size
  val defaultWidth: Int = 4000
  val minWidth: Int = 100
  val maxWidth: Int = 8000

  // globe center
  val center: Point = Point(.0, .0)
}
