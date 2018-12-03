package projet_top.countries

import org.scalatest.FlatSpec

/**
  * Tests pour la récupération par le nom dans la CountryDatabase
  */
//noinspection ScalaFileName
class CountryDatabaseGetByNameTest extends FlatSpec {
  val countryList: List[Country] = Country (
    countryName = "name 2",
    inhabitants = 2,
    surface = .2
  ) :: Country (
    countryName = "name 1",
    inhabitants = 1,
    surface = .1
  ) :: Nil

  val countryDatabase: CountryDatabase = CountryDatabase.fromList(countryList)

  "A countryDatabase" should "throw an NSEE when looking for a non-existent name" in {
    intercept[NoSuchElementException](
      countryDatabase.getCountryByName("")
    )
  }

  "A countryDatabase" should "return the correct country when looking for its name" in {
    val to_search = countryList(1)

    val found = countryDatabase.getCountryByName(to_search.countryName)

    assert(found.countryName == to_search.countryName)
    assert(found.inhabitants == to_search.inhabitants)
    assert(found.surface == to_search.surface)
  }
}

/**
  * Tests pour la récupération par le nom via apply dans la CountryDatabase
  */
//noinspection ScalaFileName
class CountryDatabaseApplyTest extends FlatSpec {
  val countryList: List[Country] = Country (
    countryName = "name 2",
    inhabitants = 2,
    surface = .2
  ) :: Country (
    countryName = "name 1",
    inhabitants = 1,
    surface = .1
  ) :: Nil

  val countryDatabase: CountryDatabase = CountryDatabase.fromList(countryList)

  "A countryDatabase" should "throw an NSEE when looking for a non-existent name" in {
    intercept[NoSuchElementException](
      countryDatabase.apply("")
    )
  }

  "A countryDatabase" should "return the correct country when looking for its name" in {
    val to_search = countryList(1)

    val found = countryDatabase.apply(to_search.countryName)

    assert(found.countryName == to_search.countryName)
    assert(found.inhabitants == to_search.inhabitants)
    assert(found.surface == to_search.surface)
  }
}