package projet_top.airports

import org.scalatest.FlatSpec

object AirportDatabaseTestObjects {
  val airportList: List[Airport] = Airport(
    airportId = 2,
    name = "name 2",
    city = "city 2",
    countryName = "country 2",
    latitude = .2,
    longitude = .2
  ) :: Airport(
    airportId = 1,
    name = "name 1",
    city = "city 1",
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

  "An AirportDatabase" should "throw an NSEE when looking for a non-existent ID" in {
    intercept[NoSuchElementException](
      AirportDatabaseTestObjects.airportDatabase.getAirportById(-1)
    )
  }

  "An AirportDatabase" should "return the correct airport when looking for its ID" in {
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

  "An AirportDatabase" should "throw an NSEE when looking for a non-existent ID" in {
    intercept[NoSuchElementException](
      AirportDatabaseTestObjects.airportDatabase.apply(-1)
    )
  }

  "An AirportDatabase" should "return the correct airport when looking for its ID" in {
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

  "An AirportDatabase" should "return a valid list of its airports" in {
    assert(AirportDatabaseTestObjects.airportDatabase.toList.sortBy(airport => airport.airportId)
      == AirportDatabaseTestObjects.airportList.sortBy(airport => airport.airportId))
  }

}


/**
  * Tests pour la vérifier si un élément est contenu dans CountryDatabase
  */
//noinspection ScalaFileName
class AirportDatabaseContainsTest extends FlatSpec {

  "An airportDatabase" should "return false when non-existent airport is searched" in {
    val to_search = Airport(
      airportId = 0,
      name = "name 0",
      city = "city 0",
      countryName = "country 0",
      latitude = .0,
      longitude = .0
    )

    val found = AirportDatabaseTestObjects.airportDatabase.contains(to_search)

    assert(!found)
  }

  "An airportDatabase" should "return false when non-existent airport is searched with its id" in {
    val to_search = Airport (
      airportId = 0,
      name = "name 0",
      city = "city 0",
      countryName = "country 0",
      latitude = .0,
      longitude = .0
    )

    val found = AirportDatabaseTestObjects.airportDatabase.contains(to_search.airportId)

    assert(!found)
  }

  "An airportDatabase" should "return true when a stored airport is searched" in {
    val to_search = AirportDatabaseTestObjects.airportList(1)

    val found = AirportDatabaseTestObjects.airportDatabase.contains(to_search)

    assert(found)
  }

  "An airportDatabase" should "return true when a stored airport is searched with its id" in {
    val to_search = AirportDatabaseTestObjects.airportList(1)

    val found = AirportDatabaseTestObjects.airportDatabase.contains(to_search.airportId)

    assert(found)
  }
}
