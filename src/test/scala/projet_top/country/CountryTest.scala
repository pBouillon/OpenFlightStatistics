package projet_top.country

import org.scalatest.FlatSpec

/**
  * Tests pour le constructeur de Country
  */
//noinspection ScalaFileName
class CountryConstructorSpec extends FlatSpec {

  behavior of "Country instantiation"

  it should "throw an IAE when instantiated with an empty country name" in {
      intercept[IllegalArgumentException] {
      Country(countryName = "", inhabitants = 0, surface = 0.0)
    }
  }

  it should "throw an IAE when instantiated with a negative number of inhabitants" in {
    intercept[IllegalArgumentException] {
      Country(countryName = "TestCountry", inhabitants = -1, surface = 0.0)
    }
  }

  it should "throw an IAE when instantiated with a negative surface" in {
    intercept[IllegalArgumentException] {
      Country(countryName = "TestCountry", inhabitants = 0, surface = -1)
    }
  }

  it should "not throw anything when correctly instantiated" in {
    Country(countryName = "TestCountry", inhabitants = 9001, surface = 1337.42)
  }
}
