package projet_top.countries

// TODO ???
/**
  * Objet immuable qui contient les données relatives à un pays. Est utilisée principalement dans la
  * classe CountryDatabase.
  *
  * @param countryName nom du pays, sert d'identifiant unique
  * @param inhabitants nombre d'habitants du pays au miles²
  * @param surface superficie du pays en km²
  */
case class Country(countryName: String, inhabitants: Long, surface: Double)
