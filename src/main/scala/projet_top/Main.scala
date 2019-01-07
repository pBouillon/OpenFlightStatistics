package projet_top

import java.awt.Color
import java.io.File

import projet_top.projection.backmap_providers.D3BackmapProvider
import projet_top.projection.projectors.EquiRectangularLat0Projector
import projet_top.projection.{MapCreator, markers}

object Main {
  def main(args: Array[String]): Unit = {
    val myWidth = 4000
    val osloPos = globe.Point(59.913868, 10.752245)
    val myProjector = new EquiRectangularLat0Projector(osloPos)
    val myBackmapProvider = new D3BackmapProvider("http://d3backmapprovider.westeurope.azurecontainer.io:8080/")
    val myMapCreator = new MapCreator(myProjector, myBackmapProvider)(myWidth)
    val rioPos = globe.Point(-22.906847, -43.172897)
    val parisPos = globe.Point(48.856613, 2.352222)
    val myMarkerSize = 20
    val myMarker1 = markers.Round(Color.red, markers.Outline(3))(myMarkerSize)
    val myMarker2 = markers.Square(Color.blue, markers.Filled)(myMarkerSize)
    myMapCreator.plotObject(rioPos)(myMarker1)
    myMapCreator.plotObject(parisPos)(myMarker2)
    myMapCreator.saveToFile(new File("paris_rio_oslo.png"))

    println("Done!")
  }
}
