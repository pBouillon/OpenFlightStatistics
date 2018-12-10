package projet_top.airports

import org.scalatest.FlatSpec

object AirportDatabaseTestObjects {
  val airportList: List[Airport] = Airport(
    airportId = 2,
    airportName = "name 2",
    cityName = "city 2",
    countryName = "country 2",
    latitude = .2,
    longitude = .2
  ) :: Airport(
    airportId = 1,
    airportName = "name 1",
    cityName = "city 1",
    countryName = "country 1",
    latitude = .1,
    longitude = .1
  ) :: Nil

  val airportDatabase: AirportDatabase = AirportDatabase.fromList(airportList)
}

/**
  * Tests pour la récupération par ID dans la AirportDatabase
  */
//noinspection ScalaFileName
class AirportDatabaseGetAirportByIdTest extends FlatSpec {

  behavior of "The AirportDatabase getAirportById method"

  it should "throw an NSEE when looking for a non-existent ID" in {
    intercept[NoSuchElementException](
      AirportDatabaseTestObjects.airportDatabase.getAirportById(-1)
    )
  }

  it should "return the correct airport when looking for its ID" in {
    val to_search = AirportDatabaseTestObjects.airportList(1)
    val found = AirportDatabaseTestObjects.airportDatabase.getAirportById(to_search.airportId)
    assert(found == to_search)
  }

}


/**
  * Tests pour la récupération par ID dans la AirportDatabase
  */
//noinspection ScalaFileName
class AirportDatabaseApplyTest extends FlatSpec {

  behavior of "The AirportDatabase apply method"

  it should "throw an NSEE when looking for a non-existent ID" in {
    intercept[NoSuchElementException](
      AirportDatabaseTestObjects.airportDatabase.apply(-1)
    )
  }

  it should "return the correct airport when looking for its ID" in {
    val to_search = AirportDatabaseTestObjects.airportList(1)
    val found = AirportDatabaseTestObjects.airportDatabase.apply(to_search.airportId)
    assert(found == to_search)
  }

}


/**
  * Tests pour la conversion d'un AirportDatabase en List
  */
//noinspection ScalaFileName
class AirportDatabaseToListTest extends FlatSpec {

  behavior of "The AirportDatabase toList method"

  it should "return a valid list of its airports" in {
    assert(AirportDatabaseTestObjects.airportDatabase.toList.sortBy(airport => airport.airportId)
      == AirportDatabaseTestObjects.airportList.sortBy(airport => airport.airportId))
  }

}


/**
  * Tests pour la vérifier si un élément est contenu dans CountryDatabase
  */
//noinspection ScalaFileName
class AirportDatabaseContainsTest extends FlatSpec {

  behavior of "The AirportDatabase contains method"

  it should "throw an RE when looking for a different airport with same ID" in {
      val impossibleCity = "Gotham City"
      val reference = AirportDatabaseTestObjects.airportList.head

      val to_search = Airport (
        airportId = reference.airportId,
        airportName = reference.airportName,
        cityName = impossibleCity,
        countryName = reference.countryName,
        latitude = reference.latitude,
        longitude = reference.longitude
      )

    intercept[RuntimeException](
      AirportDatabaseTestObjects.airportDatabase.contains(to_search)
    )
  }

  it should "return false when non-existent airport is searched" in {
    val to_search = Airport(
      airportId = 0,
      airportName = "name 0",
      cityName = "city 0",
      countryName = "country 0",
      latitude = .0,
      longitude = .0
    )

    val found = AirportDatabaseTestObjects.airportDatabase.contains(to_search)

    assert(!found)
  }

  it should "return false when non-existent airport is searched with its id" in {
    val to_search = Airport (
      airportId = 0,
      airportName = "name 0",
      cityName = "city 0",
      countryName = "country 0",
      latitude = .0,
      longitude = .0
    )

    val found = AirportDatabaseTestObjects.airportDatabase.contains(to_search.airportId)

    assert(!found)
  }

  it should "return true when a stored airport is searched" in {
    val to_search = AirportDatabaseTestObjects.airportList(1)

    val found = AirportDatabaseTestObjects.airportDatabase.contains(to_search)

    assert(found)
  }

  it should "return true when a stored airport is searched with its id" in {
    val to_search = AirportDatabaseTestObjects.airportList(1)

    val found = AirportDatabaseTestObjects.airportDatabase.contains(to_search.airportId)

    assert(found)
  }
}
