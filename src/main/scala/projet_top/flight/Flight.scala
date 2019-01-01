package projet_top.flight

import java.time.format.DateTimeFormatter
import java.time.{Duration, ZoneId, ZonedDateTime}

import projet_top.airport.Airport
import projet_top.globe.Utils.distance
import projet_top.Utils.rightPad

case class Flight(fromAirport: Airport, fromTimeZone: ZoneId, toAirport: Airport, toTimeZone: ZoneId, departureDateTime: ZonedDateTime, expectedDuration: Duration, companyName: String) extends PathLike {
  override def toString: String = {
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm (O)")

    val fromTimeString = departureDateTime.withZoneSameInstant(fromTimeZone).format(formatter)
    val toTimeString = departureDateTime.plus(expectedDuration).withZoneSameInstant(toTimeZone).format(formatter)

    val leftFieldsLength = List(fromAirport.airportName.length,
                                fromAirport.cityName.length + 2 + fromAirport.countryName.length,
                                fromTimeString.length).max max 9

    val separator = "  -->  "
    val blankSeparator = " " * separator.length
    val totalSeconds = expectedDuration.getSeconds
    val repr =
      rightPad(fromAirport.airportName, leftFieldsLength) + separator + toAirport.airportName + "\n" +
      rightPad(fromAirport.cityName + ", " + fromAirport.countryName, leftFieldsLength) + blankSeparator + toAirport.cityName + ", " + toAirport.countryName + "\n" +
      rightPad(fromTimeString, leftFieldsLength) + blankSeparator + toTimeString + "\n" +
      rightPad("distance:", leftFieldsLength) + blankSeparator + "%.0f".format(distance(fromAirport, toAirport)) + " km\n" +
      rightPad("duration:", leftFieldsLength) + blankSeparator + "%02dh%02d".format(totalSeconds / 3600, Math.round((totalSeconds % 3600).toDouble / 60.0).toInt) + "\n" +
      s"by ${companyName}"
    repr
  }

  def toPath: Path = Path(this.fromAirport, this.toAirport)
}
