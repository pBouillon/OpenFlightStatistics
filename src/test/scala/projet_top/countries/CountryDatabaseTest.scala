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

    assert(found == to_search)
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

    assert(found == to_search)
  }
}

/**
  * Tests pour la vérifier si un élément est contenu dans CountryDatabase
  */
//noinspection ScalaFileName
class CountryDatabaseContainsTest extends FlatSpec {
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

  "A countryDatabase" should "return false when non-existent country is searched" in {
    val to_search = Country (
      countryName = "name 0",
      inhabitants = 0,
      surface = .0
    )

    val found = countryDatabase.contains(to_search)

    assert(!found)
  }

  "A countryDatabase" should "return false when non-existent country is searched with its name" in {
    val to_search = Country (
      countryName = "name 0",
      inhabitants = 0,
      surface = .0
    )

    val found = countryDatabase.contains(to_search.countryName)

    assert(!found)
  }

  "A countryDatabase" should "return true when a stored country is searched" in {
    val to_search = countryList(1)

    val found = countryDatabase.contains(to_search)

    assert(found)
  }

  "A countryDatabase" should "return true when a stored country is searched with its name" in {
    val to_search = countryList(1)

    val found = countryDatabase.contains(to_search.countryName)

    assert(found)
  }
}


/**
  * Tests pour la conversion d'une CountryDatabase en List
  */
//noinspection ScalaFileName
class CountryDatabaseToListTest extends FlatSpec {
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

  "A countryDatabase" should "return a valid list of its countries" in {
    assert(countryDatabase.toList.sortBy(country => country.countryName)
      == countryList.sortBy(country => country.countryName))
  }

}