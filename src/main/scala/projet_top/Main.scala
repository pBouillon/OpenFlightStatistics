package projet_top

import java.awt.Color
import java.io.File
import java.time.{Duration, LocalDateTime, Month, ZoneId}

import projet_top.projection.backmap_providers.D3BackMapProvider
import projet_top.projection.projectors.EquiRectangularProjector
import projet_top.projection.{MapCreator, markers}

object Main {
  def main(args: Array[String]): Unit = {
    val myWidth = 4000
    val osloPos = globe.Point(59.913868, 10.752245)
    val myProjector = new EquiRectangularProjector(osloPos)
    val myBackmapProvider = new D3BackMapProvider("http://d3backmapprovider.westeurope.azurecontainer.io:8080/")
    val myMapCreator = new MapCreator(myProjector, myBackmapProvider)(myWidth)

    val parisAirport = airport.Airport(0, "Paris-Roissy", "Roissy", "France", 48.856613, 2.352222)
    val rioAirport = airport.Airport(1, "Rio-Antonio Carlos Jobim", "Rio de Janeiro", "Brazil", -22.906847, -43.172897)
    val myMarkerSize = 20
    val myMarkerParis = markers.Round(Color.red, markers.Outline(3))(myMarkerSize)
    val myMarkerRio = markers.Square(Color.blue, markers.Filled)(myMarkerSize)
    val parisTimeZone = ZoneId.of("Europe/Paris")
    val rioTimeZone = ZoneId.of("Brazil/East")
    val departureDateTime = LocalDateTime.of(2019, Month.JANUARY, 2, 10, 25).atZone(parisTimeZone)
    val expectedDuration = Duration.ofMinutes(11 * 60 + 30)
    val companyName = "AirFrance"

    val myParisRioFlight = flight.Flight(parisAirport, parisTimeZone, rioAirport, rioTimeZone, departureDateTime, expectedDuration, companyName)

    val myParisRioPath = flight.Path(parisAirport, rioAirport)

    myMapCreator.plotPathLike(myParisRioPath)(myMarkerParis, myMarkerRio, fontSize = 40)

    myMapCreator.saveToFile(new File("paris_rio_oslo.png"))

    println("Done!")
  }
}
