package projet_top.cli.utils

import java.awt.Color

import projet_top.globe.Point
import projet_top.projection.backmap_providers.{BackmapProvider, D3BackmapProvider}

object MapCreation {
  // map provider
  val defaultBackmapProvider: BackmapProvider =
    new D3BackmapProvider("http://d3backmapprovider.westeurope.azurecontainer.io:8080/", style = Some(
      """
        |body {
        |  background-color: #2b7f7f;
        |}
        |.country{
        |  fill: #99c542;   /* country colour */
        |  stroke: #aaaaaa; /* country border colour */
        |  stroke-width: 1; /* country border width */
        |}
        |
      """.stripMargin
    ))

  // marker
  val defaultMarkerColorValue: String = "#D34747"
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
