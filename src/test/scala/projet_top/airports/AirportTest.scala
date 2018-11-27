package projet_top.airports

import org.scalatest.FlatSpec

/**
  * Tests pour le constructeur de Airport
  */
//noinspection ScalaFileName
class AirportConstructorSpec extends FlatSpec {
  "An airport instantiated with an empty airport name" should "throw an IAE" in {
    intercept[IllegalArgumentException] {
      Airport(0, "", "TestCity", "TestCountry", 0.0, 0.0)
    }
  }
  "An airport instantiated with an empty city name" should "throw an IAE" in {
    intercept[IllegalArgumentException] {
      Airport(0, "TestAirport", "", "TestCountry", 0.0, 0.0)
    }
  }
  "An airport instantiated with an empty country name" should "throw an IAE" in {
    intercept[IllegalArgumentException] {
      Airport(0, "TestAirport", "TestCity", "", 0.0, 0.0)
    }
  }
  "An airport instantiated with a latitude < -90" should "throw an IAE" in {
    intercept[IllegalArgumentException] {
      Airport(0, "TestAirport", "TestCity", "TestCountry", -98.0, 0.0)
    }
  }
  "An airport instantiated with a latitude > 90" should "throw an IAE" in {
    intercept[IllegalArgumentException] {
      Airport(0, "TestAirport", "TestCity", "TestCountry", 198.0, 0.0)
    }
  }
  "An airport instantiated with a longitude < -180" should "throw an IAE" in {
    intercept[IllegalArgumentException] {
      Airport(0, "TestAirport", "TestCity", "TestCountry", 0.0, -192.1)
    }
  }
  "An airport instantiated with a longitude > 180" should "throw an IAE" in {
    intercept[IllegalArgumentException] {
      Airport(0, "TestAirport", "TestCity", "TestCountry", 0.0, 212.3)
    }
  }
  "An airport instantiated with a non-empty airport name, a non-empty city name, a non-empty country name, and a valid latitude and longitude" should "not throw any exception" in {
    Airport(0, "TestAirport", "TestCity", "TestCountry", -34.123, 112.3)
  }
}

