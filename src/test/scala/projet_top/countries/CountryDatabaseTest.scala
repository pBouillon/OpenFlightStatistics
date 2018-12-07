package projet_top.countries

import org.scalatest.FlatSpec

object CountryDatabaseTestObjects {
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
}

/**
  * Tests pour la récupération par le nom dans la CountryDatabase
  */
//noinspection ScalaFileName
class CountryDatabaseGetByNameTest extends FlatSpec {
  "A countryDatabase" should "throw an NSEE when looking for a non-existent name" in {
    intercept[NoSuchElementException](
      CountryDatabaseTestObjects.countryDatabase.getCountryByName("")
    )
  }

  "A countryDatabase" should "return the correct country when looking for its name" in {
    val to_search = CountryDatabaseTestObjects.countryList(1)

    val found = CountryDatabaseTestObjects.countryDatabase.getCountryByName(to_search.countryName)

    assert(found == to_search)
  }
}

/**
  * Tests pour la récupération par le nom via apply dans la CountryDatabase
  */
//noinspection ScalaFileName
class CountryDatabaseApplyTest extends FlatSpec {

  "A countryDatabase" should "throw an NSEE when looking for a non-existent name" in {
    intercept[NoSuchElementException](
      CountryDatabaseTestObjects.countryDatabase.apply("")
    )
  }

  "A countryDatabase" should "return the correct country when looking for its name" in {
    val to_search = CountryDatabaseTestObjects.countryList(1)

    val found = CountryDatabaseTestObjects.countryDatabase.apply(to_search.countryName)

    assert(found == to_search)
  }
}

/**
  * Tests pour la vérifier si un élément est contenu dans CountryDatabase
  */
//noinspection ScalaFileName
class CountryDatabaseContainsTest extends FlatSpec {

  "An countryDatabase" should "throw an RE when looking for a different country with same ID" in {
    val impossibleSurface = 1000000000
    val reference = CountryDatabaseTestObjects.countryList.head

    val to_search = Country (
      countryName = reference.countryName,
      inhabitants = reference.inhabitants,
      surface = impossibleSurface
    )

    intercept[RuntimeException](
      CountryDatabaseTestObjects.countryDatabase.contains(to_search)
    )
  }

  "A countryDatabase" should "return false when non-existent country is searched" in {
    val to_search = Country (
      countryName = "name 0",
      inhabitants = 0,
      surface = .0
    )

    val found = CountryDatabaseTestObjects.countryDatabase.contains(to_search)

    assert(!found)
  }

  "A countryDatabase" should "return false when non-existent country is searched with its name" in {
    val to_search = Country (
      countryName = "name 0",
      inhabitants = 0,
      surface = .0
    )

    val found = CountryDatabaseTestObjects.countryDatabase.contains(to_search.countryName)

    assert(!found)
  }

  "A countryDatabase" should "return true when a stored country is searched" in {
    val to_search = CountryDatabaseTestObjects.countryList(1)

    val found = CountryDatabaseTestObjects.countryDatabase.contains(to_search)

    assert(found)
  }

  "A countryDatabase" should "return true when a stored country is searched with its name" in {
    val to_search = CountryDatabaseTestObjects.countryList(1)

    val found = CountryDatabaseTestObjects.countryDatabase.contains(to_search.countryName)

    assert(found)
  }
}


/**
  * Tests pour la conversion d'une CountryDatabase en List
  */
//noinspection ScalaFileName
class CountryDatabaseToListTest extends FlatSpec {

  "A countryDatabase" should "return a valid list of its countries" in {
    assert(CountryDatabaseTestObjects.countryDatabase.toList.sortBy(country => country.countryName)
      == CountryDatabaseTestObjects.countryList.sortBy(country => country.countryName))
  }

}