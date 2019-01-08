package projet_top.airport

import org.scalatest.FlatSpec

object AirportDistanceMapTestObject {

  val airportDistanceMap = AirportDatabaseTestObjects.airportDatabase.getDistanceMap

}

/**
  * Tests pour la récupération de la distance entre les deux aéroports choisis, identifiés par leur ID.
  */
//noinspection ScalaFileName
class AirportDistanceMapGetDistanceBetweenTest extends FlatSpec {

  behavior of "The AirportDistanceMap getDistanceBetween method"

  it should "throw an NSEE when looking for a non-existent ID" in {
    val invalidAirportId = -1

    intercept[NoSuchElementException]{
      AirportDistanceMapTestObject.airportDistanceMap
        .getDistanceBetween(invalidAirportId)(invalidAirportId)
    }
  }

  it should "throw an NSEE when looking for a non-existent ID and an existent one" in {
    val invalidAirportId = -1
    val validAirportId1 = 1

    intercept[NoSuchElementException]{
      AirportDistanceMapTestObject.airportDistanceMap
        .getDistanceBetween(validAirportId1)(invalidAirportId)
    }
  }


  it should "throw a distance between airport in the map thanks to ID" in {
    val validAirportId1 = 1
    val validAirportId2 = 2
    val expected = 15.725331604303992

    val ret = AirportDistanceMapTestObject.airportDistanceMap
      .getDistanceBetween(validAirportId1)(validAirportId2)

    assert(ret == expected)
  }

  /**
    * Tests pour la récupération de la distance entre les deux aéroports choisis, identifiés par leur nom.
    */
  it should "throw an NSEE when looking for a non-existent airport name" in {
    val impossibleAirport = Airport(-1, "Gotham Aiport", "Gotham City", "Gotham Name", -1, 1)

    intercept[NoSuchElementException]{
      AirportDistanceMapTestObject.airportDistanceMap
        .getDistanceBetween(impossibleAirport)(impossibleAirport)
    }
  }

  it should "throw an NSEE when looking for a non-existent airport name and an existent one" in {
    val impossibleAirport = Airport(-1, "Gotham Aiport", "Gotham City", "Gotham Name", -1, 1)
    val possibleAirport = AirportDatabaseTestObjects.airportDatabase
      .getAirportById(1)

    intercept[NoSuchElementException]{
      AirportDistanceMapTestObject.airportDistanceMap
        .getDistanceBetween(possibleAirport)(impossibleAirport)
    }
  }

  it should "throw a distance between airports in the map" in {
    val expected = 15.725331604303992
    val possibleAirport1 = AirportDatabaseTestObjects.airportDatabase
      .getAirportById(1)
    val possibleAirport2 = AirportDatabaseTestObjects.airportDatabase
      .getAirportById(2)

    val ret = AirportDistanceMapTestObject.airportDistanceMap
        .getDistanceBetween(possibleAirport1)(possibleAirport2)

    assert(ret == expected)
  }
}

/**
  * Tests pour la récupération de la distance entre les deux aéroports choisis, identifiés par leur ID(apply method).
  */

  class AirportDistanceMapApplyTest extends FlatSpec {

    behavior of "The AirportDistance apply method"

    it should "throw an NSEE when looking for a non-existent distance between airports in the map" in {
      val invalidAirportId = -1

      intercept[NoSuchElementException] {
        AirportDistanceMapTestObject.airportDistanceMap
          .apply(invalidAirportId)(invalidAirportId)
      }
    }

    it should "throw an NSEE when looking for an existent non-existent distance between airports in the map" in {
      val invalidAirportId = -1
      val validAirportId1 = 1

      intercept[NoSuchElementException]{
        AirportDistanceMapTestObject.airportDistanceMap
          .apply(validAirportId1)(invalidAirportId)
      }
    }

    it should "throw a distance between airport in the map" in {
      val expected = 15.725331604303992
      val validAirportId1 = 1
      val validAirportId2 = 2

      val ret = AirportDistanceMapTestObject.airportDistanceMap.
        apply(validAirportId1)(validAirportId2)

      assert(ret == expected)
    }

    it should "throw an NSEE when looking for a non-existent airport name" in {
      val impossibleAirport = Airport(-1, "Gotham Aiport", "Gotham City", "Gotham Name", -1, 1)

      intercept[NoSuchElementException]{
        AirportDistanceMapTestObject.airportDistanceMap
          .apply(impossibleAirport)(impossibleAirport)
      }
    }

    it should "throw an NSEE when looking for a non-existent airport name and an existent one" in {
      val impossibleAirport = Airport(-1, "Gotham Aiport", "Gotham City", "Gotham Name", -1, 1)
      val possibleAirport = AirportDatabaseTestObjects.airportDatabase
        .getAirportById(1)

      intercept[NoSuchElementException]{
        AirportDistanceMapTestObject.airportDistanceMap
          .apply(possibleAirport)(impossibleAirport)
      }
    }

    it should "throw a distance between airports in the map" in {
      val expected = 15.725331604303992
      val possibleAirport1 = AirportDatabaseTestObjects.airportDatabase
        .getAirportById(1)
      val possibleAirport2 = AirportDatabaseTestObjects.airportDatabase
        .getAirportById(2)

      val ret = AirportDistanceMapTestObject.airportDistanceMap
        .apply(possibleAirport1)(possibleAirport2)

      assert(ret == expected)
    }

  }



