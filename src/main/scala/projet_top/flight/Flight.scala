package projet_top.flight

import java.time.format.DateTimeFormatter
import java.time.{Duration, ZoneId, ZonedDateTime}


import projet_top.airport.Airport
import projet_top.globe.Utils.distance

case class Flight(fromAirport: Airport, fromTimeZone: ZoneId, toAirport: Airport, toTimeZone: ZoneId, departureTime: ZonedDateTime, expectedDuration: Duration, companyName: String) {
  override def toString: String = {
    val formatter = DateTimeFormatter.ISO_DATE_TIME

    val fromTimeString = departureTime.withZoneSameInstant(fromTimeZone).format(formatter)
    val toTimeString = departureTime.plus(expectedDuration).withZoneSameInstant(toTimeZone).format(formatter)

    val leftFieldsLength = List(fromAirport.airportName.length,
                                fromAirport.cityName.length + 2 + fromAirport.countryName.length,
                                fromTimeString.length).max max 9

    def rightPad(string: String, length: Int): String = {
      if (string.length > length) throw new IllegalArgumentException(s"String length (${string.length}) > specified length (${length})!")
      string + (" " * (length - string.length))
    }

    val separator = "  -->  "
    val totalSeconds = expectedDuration.getSeconds
    val repr =
      rightPad(fromAirport.airportName, leftFieldsLength) + separator + toAirport.airportName + "\n" +
      rightPad(fromAirport.cityName + ", " + fromAirport.countryName, leftFieldsLength) + separator + toAirport.cityName + ", " + toAirport.countryName + "\n" +
      rightPad(fromTimeString, leftFieldsLength) + separator + toTimeString + "\n" +
      rightPad("distance:", leftFieldsLength) + "  " + distance(fromAirport, toAirport) + "\n" +
      rightPad("duration:", leftFieldsLength) + "  " + "%02d:%02d:%02d".format(totalSeconds / 3600, (totalSeconds % 3600) / 60, totalSeconds % 60)
    repr
  }
}
