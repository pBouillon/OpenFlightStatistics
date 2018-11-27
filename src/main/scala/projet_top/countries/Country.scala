package projet_top.countries

/**
  * Objet immuable qui contient les données relatives à un pays. Est utilisée principalement dans la
  * classe CountryDatabase.
  *
  * @param countryName nom du pays, sert d'identifiant unique
  * @param inhabitants nombre d'habitants du pays
  * @param surface superficie du pays en km²
  */
case class Country(countryName: String, inhabitants: Long, surface: Double) {
  require(
    !countryName.isEmpty,
    "country name cannot be empty !"
  )
  require(
    inhabitants >= 0,
    "country inhabitants cannot be a negative value"
  )
  require(
    surface >= 0.0,
    "country surface cannot be a negative value"
  )
}
