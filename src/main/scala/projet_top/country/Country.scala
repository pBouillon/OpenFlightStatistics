package projet_top.country

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

  /**
    * Aperçu de l'objet
    * @return un aperçu de l'objet
    */
  override def toString: String = {
    "Country [\n" +
    s"    countryName     ${this.countryName}\n" +
    s"    inhabitants     ${this.inhabitants}\n" +
    s"    surface         ${this.surface}\n" +
    "]"
  }
}
