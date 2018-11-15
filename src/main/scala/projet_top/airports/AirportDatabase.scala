package projet_top.airports

import java.io._

import projet_top.airports.airport_filters.AirportFilter
import projet_top.countries.Country

/**
  * Objet compagnon de la classe projet_top.airports.AirportDatabase. Sert à contenir les méthodes et champs statiques.
  */
object AirportDatabase {
  /**
    * Créé un objet projet_top.airports.AirportDatabase en lisant le fichier .csv fourni en paramètre.
    * @param inputFile fichier CSV dont les données sont extraites pour construire l'projet_top.airports.AirportDatabase
    * @return un objet projet_top.airports.AirportDatabase
    */
  def loadFromCSV(inputFile: File): AirportDatabase = {
    // TODO
    new AirportDatabase(Map())
  }

  def fromList(airports: List[Airport]): AirportDatabase = {
    // TODO
    new AirportDatabase(Map())
  }
}

/**
  * Classe principale qui va contenir nos données d'aéroport et qui va contenir les méthodes de traitement sur cette base de données.
  * @param _internalDatabase Map airportID <=> objets projet_top.airports.Airport contenants les données des aéroports
  */
class AirportDatabase (_internalDatabase: Map[Int, Airport]) {
  /**
    * Retourne l'objet projet_top.airports.Airport correspondant à l'ID choisi, et lève une exception si cet ID n'est pas dans la base de données.
    * @param airportId ID de l'aéroport à récupérer
    * @return l'objet projet_top.airports.Airport correspondant à l'aéroport demandé.
    */
  def getAirportById(airportId: Int): Airport = {
    // TODO
    Airport(0, "", "", "", 0.0, 0.0)
  }

  /**
    * Retourne les aéroports correspondants au filtre choisi. Retourne une liste vide si aucun ne correspond. Si aucun filtre n'est spécifié, retourne la liste complète des aéroports.
    * @param filter filtre à appliquer sur la base de données de tous les aéroports
    * @return la liste des aéroports correspondants aux filtre choisi
    */
  def getSubset(filter: AirportFilter = airport_filters.All): AirportDatabase = {
    // TODO
    new AirportDatabase(Map())
  }

  /**
    * Retourne un objet AirportDistanceMap qui est une carte des distances entre les aéroports de l'AirportDatabase courante.
    * @return un objet AirportDistanceMap qui est une carte des distances entre les aéroports de l'AirportDatabase courante
    */
  def getDistanceMap: AirportDistanceMap = {
    // TODO
    new AirportDistanceMap(Map(), Array())
  }

  /**
    * Retourne la liste des objets Airport contenus dans l'AirportDatabase courante
    * @return la liste des objets Airport contenus dans l'AirportDatabase courante
    */
  def toList: List[Airport] = {
    // TODO
    List()
  }

  /**
    * Retourne la densité d'aéroports dans le pays choisi, par rapport au champ extrait par la fonction againstWhat sur l'objet Country.
    * Par exemple, pour la densité d'aéroports par rapport au nombre d'habitants en France,
    *     val france = Country("France", 65018000, 672051)
    *     val density = airportDatabase.getDensityIn(france, _.inhabitants)
    * @param country objet Country qui représente le pays dans lequel on veut effectuer la mesure
    * @param againstWhat fonction qui sélectionne un champ sur l'objet Country avec lequel sera calculé la densité
    * @return la densité d'aéroports en fonction du champ extrait par la fonction againstWhat
    */
  def getDensityIn(country: Country, againstWhat: Country => Double): Double = {
    // TODO
    0.0
  }
}
