package projet_top.airports

import org.scalatest.FlatSpec

/**
  * Tests pour la récupération par ID dans la AirportDatabase
  */
//noinspection ScalaFileName
class AirportDatabaseGetByIdTest extends FlatSpec {
  val airportList: List[Airport] = new Airport(
    airportId = 1,
    name = "name 1",
    city = "city 1",
    countryName = "country 1",
    latitude = .1,
    longitude = .1
  ) :: new Airport(
    airportId = 2,
    name = "name 2",
    city = "city 2",
    countryName = "country 2",
    latitude = .2,
    longitude = .2
  ) :: Nil

  val airportDatabase: AirportDatabase = AirportDatabase.fromList(airportList)

  "An AirportDatabase" should "throw an NSEE when looking for a non-existent ID" in {
    intercept[NoSuchElementException](
      airportDatabase.getAirportById(-1)
    )
  }

  "An AirportDatabase" should "return the correct airport when looking for its ID" in {
    val to_search = airportList(1)

    val found = airportDatabase.getAirportById(to_search.airportId)

    assert(found.airportId == to_search.airportId)
    assert(found.name == to_search.name)
    assert(found.city == to_search.city)
    assert(found.countryName == to_search.countryName)
    assert(found.latitude == to_search.latitude)
    assert(found.longitude == to_search.longitude)
  }

}
