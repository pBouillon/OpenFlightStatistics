# Projet TOP

## Ressources

### Liste des meilleures librairies SCALA

- [Awesome Scala](https://github.com/lauris/awesome-scala)

### Bibliothèque pour lire les fichiers CSV (respect de *don't reinvent the wheel*)

- [`scala-csv`](https://github.com/tototoshi/scala-csv) -- selon Awesome Scala

## Implémentation des questions

### Question 1

```scala
import projet_top.airports.AirportDatabase
import java.io.File

val maBase = AirportDatabase.loadFromCSV(new File("monFichierCSV.csv"))
```

### Question 2

```scala
import projet_top.airports.Airport

val maDistance = Airport.distance(monAeroport1, monAeroport2)
val maMatrice = maBase.getDistanceMap
```

### Question 3

```scala
val distMinimale = maMatrice.minDistance
val distMaximale = maMatrice.maxDistance
val distMoyenne = maMatrice.avgDistance
val distMediane = maMatrice.medianDistance
val ecartType = maMatrice.stdDev
```

### Question 4

```scala
import projet_top.airports.airport_filters._
import projet_top.airports.airport_filters.Hemisphere.HemisphereChoice._

val maMatricePlusPetite = maBase.getSubset(CountryNames(List("Australia")) || Hemisphere(North)).getDistanceMap
// On peut aussi choisir d'autres filtres...
val distMinimale = maMatricePlusPetite.minDistance
// ...
```

### Question 5

```scala
import projet_top.countries.CountryDatabase
import java.io.File

val maBaseDePays = CountryDatabase.loadFromCSV(new File("monFichierCSVDePays.csv"))
val maDensiteEnFranceParHabitant = maBase.getDensityIn(maBaseDePays.getCountryByName("France"), _.inhabitants)
```
