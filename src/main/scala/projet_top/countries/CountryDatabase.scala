package projet_top.countries

import java.io.File

/**
  * Objet compagnon de la classe CountryDatabase. Contient les champs et méthodes statiques
  */
object CountryDatabase {
  /**
    * Créé un objet CountryDatabase en lisant le fichier .csv fourni en paramètre.
    * @param inputFile fichier CSV dont les données sont extraites pour construire l'objet CountryDatabase
    * @return un objet CountryDatabase
    */
  def loadFromCSV(inputFile: File): CountryDatabase = {
    // TODO
    new CountryDatabase(Map())
  }

  /**
    * Créé un objet CountryDatabase à partir de la liste d'objets Country passée en paramètres.
    * @param countries la liste d'objets Country qui sert à la construction de la base
    * @return un objet CountryDatabase contenant les mêmes pays que la liste passée en paramètres
    */
  def fromList(countries: List[Country]): CountryDatabase = {
    // TODO
    new CountryDatabase(Map())
  }
}

/**
  * Classe qui permet de contenir des objets Country et de réaliser des opérations dessus.
  * @param _internalMap map countryName <=> objets Country contenants les données des pays
  */
class CountryDatabase(_internalMap: Map[String, Country]) {
  /**
    * Retourne l'objet Country correspondant au countryName choisi, et lève une exception si ce countryName n'est pas dans la base de données.
    * @param countryName nom du pays à récupérer
    * @return l'objet Country correspondant au pays demandé
    */
  def getCountryByName(countryName: String): Country = {
    // TODO
    Country("", 0, 0.0)
  }

  /**
    * Retourne la liste des objets Country contenus dans la CountryDatabase courante
    * @return la liste des objets Country contenus dans la CountryDatabase courante
    */
  def toList: List[Country] = {
    // TODO
    List()
  }
}
