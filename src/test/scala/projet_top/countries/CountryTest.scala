package projet_top.countries

import org.scalatest.FlatSpec

//noinspection ScalaFileName
/**
  * Tests pour le constructeur de Country
  */
class CountryConstructorSpec extends FlatSpec {
  "A country instantiated with an empty country name" should "throw an IAE" in {
    intercept[IllegalArgumentException] {
      Country(countryName = "", inhabitants = 0, surface = 0.0)
    }
  }
  "A country instantiated with a negative number of inhabitants" should "throw an IAE" in {
    intercept[IllegalArgumentException] {
      Country(countryName = "TestCountry", inhabitants = -1, surface = 0.0)
    }
  }
  "A country instantiated with a negative surface" should "throw an IAE" in {
    intercept[IllegalArgumentException] {
      Country(countryName = "TestCountry", inhabitants = 0, surface = -1)
    }
  }
  "A country correctly instanciated" should "not throw anything" in {
    Country(countryName = "TestCountry", inhabitants = 9001, surface = 1337.42)
  }
}
