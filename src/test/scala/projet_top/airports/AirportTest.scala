package projet_top.airports

import org.scalatest.FlatSpec

/**
  * Tests pour le constructeur de Airport
  */
//noinspection ScalaFileName
class AirportConstructorSpec extends FlatSpec {

  behavior of "Airport instantiation"

  it should "throw an IAE when instantiated with an empty airport name" in {
    intercept[IllegalArgumentException] {
      Airport(0, "", "TestCity", "TestCountry", 0.0, 0.0)
    }
  }

  it should "throw an IAE when instantiated with an empty city name" in {
    intercept[IllegalArgumentException] {
      Airport(0, "TestAirport", "", "TestCountry", 0.0, 0.0)
    }
  }

  it should "throw an IAE when instantiated with an empty country name" in {
    intercept[IllegalArgumentException] {
      Airport(0, "TestAirport", "TestCity", "", 0.0, 0.0)
    }
  }

  it should "throw an IAE when instantiated with a latitude < -90" in {
    intercept[IllegalArgumentException] {
      Airport(0, "TestAirport", "TestCity", "TestCountry", -98.0, 0.0)
    }
  }

  it should "throw an IAE when instantiated with a latitude > 90" in {
    intercept[IllegalArgumentException] {
      Airport(0, "TestAirport", "TestCity", "TestCountry", 198.0, 0.0)
    }
  }

  it should "throw an IAE when instantiated with a longitude < -180" in {
    intercept[IllegalArgumentException] {
      Airport(0, "TestAirport", "TestCity", "TestCountry", 0.0, -192.1)
    }
  }

  it should "throw an IAE when instantiated with a longitude > 180" in {
    intercept[IllegalArgumentException] {
      Airport(0, "TestAirport", "TestCity", "TestCountry", 0.0, 212.3)
    }
  }

  it should "not throw any exception when instantiated with a non-empty airport name, a non-empty city name, " +
    "a non-empty country name, and a valid latitude and longitude" in {
    Airport(0, "TestAirport", "TestCity", "TestCountry", -34.123, 112.3)
  }
}

