package projet_top.flight

import projet_top.airport.Airport
import projet_top.Utils.rightPad
import projet_top.globe.Utils.distance

trait PathLike {
  val fromAirport: Airport
  val toAirport: Airport

  override def toString: String = {
    val leftFieldsLength = List(fromAirport.airportName.length,
                                fromAirport.cityName.length + 2 + fromAirport.countryName.length).max max 9
    val separator = "  -->  "
    val blankSeparator = " " * separator.length
    val repr =
      rightPad(fromAirport.airportName, leftFieldsLength) + separator + toAirport.airportName + "\n" +
        rightPad(fromAirport.cityName + ", " + fromAirport.countryName, leftFieldsLength) + blankSeparator + toAirport.cityName + ", " + toAirport.countryName + "\n" +
        rightPad("distance:", leftFieldsLength) + blankSeparator + "%.0f".format(distance(fromAirport, toAirport)) + " km"
    repr
  }
}
