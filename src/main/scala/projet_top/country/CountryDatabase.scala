package projet_top.country

import java.io.File

import com.github.tototoshi.csv.CSVReader

import scala.collection.immutable

/**
  * Objet compagnon de la classe CountryDatabase. Contient les champs et méthodes statiques
  */
object CountryDatabase {
  /**
    * Créé un objet CountryDatabase en lisant le fichier .csv fourni en paramètre.
    *
    * @param inputFile fichier CSV dont les données sont extraites pour construire l'objet CountryDatabase
    * @return un objet CountryDatabase
    */
  def loadFromCSV(inputFile: File): CountryDatabase = {
    var countries: List[Country] = Nil

    val reader = CSVReader.open(inputFile)

    reader.foreach(fields => {
      //noinspection ZeroIndexToHead
      countries = Country(
        countryName = fields(0),
        inhabitants = fields(1).toLong,
        surface = fields(2).toDouble
      ) :: countries
    })
    reader.close()

    CountryDatabase.fromList(countries)
  }

  /**
    * Créé un objet CountryDatabase à partir de la liste d'objets Country passée en paramètres.
    *
    * @param countries la liste d'objets Country qui sert à la construction de la base
    * @return un objet CountryDatabase contenant les mêmes pays que la liste passée en paramètres
    */
  def fromList(countries: List[Country]): CountryDatabase = {
    val countryNameToCountry: immutable.Map[String, Country] =
      countries.map((country: Country) => (country.countryName, country)).toMap
    new CountryDatabase(countryNameToCountry)
  }
}

/**
  * Classe qui permet de contenir des objets Country et de réaliser des opérations dessus.
  *
  * @param countryNameToCountry map countryName <=> objets Country contenants les données des pays
  */
//noinspection RedundantBlock
class CountryDatabase private (private val countryNameToCountry: immutable.Map[String, Country]) {
  /**
    * Retourne l'objet Country correspondant au countryName choisi, et lève une exception si ce
    * countryName n'est pas dans la base de données.
    *
    * @param countryName nom du pays à récupérer
    * @return l'objet Country correspondant au pays demandé
    */
  def getCountryByName(countryName: String): Country = {
    if (this.countryNameToCountry.contains(countryName)) {
      countryNameToCountry(countryName)
    } else {
      throw new NoSuchElementException(s"""This database doesn't contain "${countryName}"""")
    }
  }

  /**
    * Retourne l'objet Country correspondant au countryName choisi, et lève une exception si ce
    * countryName n'est pas dans la base de données.
    * Identique à CountryDatabase.getCountryByName
    *
    * @param countryName nom du pays à récupérer
    * @return l'objet Country correspondant au pays demandé
    */
  def apply(countryName: String): Country = this.getCountryByName(countryName)

  /**
    * Indique si le pays correspondant au countryName choisi est présent dans la base de données
    *
    * @param countryName le nom du pays à tester
    * @return true ssi le pays est présent dans la base de données
    */
  def contains(countryName: String): Boolean = this.countryNameToCountry.contains(countryName)

  /**
    * Indique si le pays spécifié est présent dans la base de données. Lève une exception si un pays
    * de même countryName est présent dans la base, mais avec des données différentes
    *
    * @param country le pays à tester
    * @return true ssi le pays est présent dans la base de données
    */
  def contains(country: Country): Boolean = {
    if (this.countryNameToCountry.contains(country.countryName)) {
      if (this.countryNameToCountry(country.countryName) != country) {
        throw new RuntimeException(
          s"""Internal data corrupted: country "${country.countryName}" """ +
          s"""is present in the database but with different data"""
        )
      }
      else {
        true
      }
    } else {
      false
    }
  }

  /**
    * Retourne la liste des objets Country contenus dans la CountryDatabase courante
    *
    * @return la liste des objets Country contenus dans la CountryDatabase courante
    */
  def toList: List[Country] = {
    this.countryNameToCountry.values.toList
  }

  /**
    * Aperçu de l'objet
    * @return un aperçu de l'objet
    */
  override def toString: String = {
    "CountryDatabase [\n" +
    s"    countries       ${this.countryNameToCountry.size}\n" +
    "\n]"
  }

  /**
    * Aperçu de l'objet complet
    * @return un aperçu de l'objet complet
    */
  def toStringFull: String = {
    "CountryDatabase [\n" +
      s"    countries       ${this.countryNameToCountry.size}\n" +
      this.toList.map(country => "   " + country.toString).mkString("\n") +
      "\n]"
  }
}
