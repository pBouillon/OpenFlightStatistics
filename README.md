# Projet TOP

## Groupe de projet

[![](https://img.shields.io/badge/Scala%20Architect-%40tbagrel1-green.svg?logo=scala&longCache=true&style=popout&colorB=de3423&link=https://gitlab.telecomnancy.univ-lorraine.fr/Projets_TOP/projet_top_a2&link=mailto:thomas.bagrel@telecomnancy.eu
)]() 
[![](https://img.shields.io/badge/Git%20Master-%40pBouillon-green.svg?logo=gitlab&longCache=true&style=popout&colorB=fc6d26&link=https://gitlab.telecomnancy.univ-lorraine.fr/Projets_TOP/projet_top_a2&link=mailto:pierre.bouillon@telecomnancy.eu
)]() 
[![](https://img.shields.io/badge/UML%20Master-%40fVogt-green.svg?logo=buffer&longCache=true&style=popout&colorB=4285F4&logoColor=4285F4&link=https://gitlab.telecomnancy.univ-lorraine.fr/Projets_TOP/projet_top_a2&link=mailto:florian.vogt@telecomnancy.eu
)]() 
[![](https://img.shields.io/badge/QA-%40aThouvenin-green.svg?logo=stackoverflow&longCache=true&style=popout&colorB=orange&link=https://gitlab.telecomnancy.univ-lorraine.fr/Projets_TOP/projet_top_a2&link=mailto:axel.thouvenin@telecomnancy.eu
)]() 



## Ressources

### Liste des meilleures librairies SCALA

- [Awesome Scala](https://github.com/lauris/awesome-scala)

### Bibliothèque pour lire les fichiers CSV (respect de *don't reinvent the wheel*)

- [`scala-csv`](https://github.com/tototoshi/scala-csv) -- selon Awesome Scala

## Implémentation des questions et utilisation

### Question 1 - Chargement d'une base

#### Implémentation

```scala
// chargement
val base: AirportDatabase = AirportDatabase.loadFromCSV(new File("path_to_my_base.csv"))

// vérification
println(s"Nombre d'aéroports chargés depuis la base: ${base.toList.length}")
```

#### Utilisation via CLI

```text
Voulez vous charger le fichier d'aéroports par défaut (base réduite et incomplète pour optimiser la vitesse) ?(o/N): o
Chargement ...
Chargement effectué !

*****************************************
Commandes disponibles:
  - 1) Résolution de la question 1 - chargement d'un fichier CSV
  - 2) Résolution de la question 2 - calcul de distance entre deux aéroports et matrice des distances
  - 3) Résolution de la question 3 - calcul des statistiques descriptives des aéroports
  - 4) Résolution de la question 4 - calcul des statistiques descriptives d'un sous-ensemble d'aéroports
  - 5) Résolution de la question 5 - calcul de la densité d'aéroports par rapport à la superficie d'un pays
  - 6) Résolution de la question 6 - projections d'aéroports sur une carte

  - 8) Afficher les commandes disponibles
  - 0) Quitter l'application
*****************************************
    
projet_top > 1
    +-----------
    | Question 1: affichage du contenu du fichier CSV

    Votre base contient 1275 aéroport(s), les afficher ? (o/N): N

projet_top > 
```

### Question 2 - calcul de distance entre deux aéroports et matrice des distances

#### Implémentation

```scala
val airport1: Airport = ...
val airport2: Airport = ...

// calcul de la distance entre les aéroports
val distance = Utils.distance(airport1, airport2)
println(s"La distance entre ces deux aéroports est de: ${distance} km.\n")

// calcul de la matrice des distances
val matrice = Cli.base.getDistanceMap
println(s"DistanceMap résultante: ${matrice}")
```

#### Utilisation via CLI 

```text
Voulez vous charger le fichier d'aéroports par défaut (base réduite et incomplète pour optimiser la vitesse) ?(o/N): o
Chargement ...
Chargement effectué !

*****************************************
Commandes disponibles:
  - 1) Résolution de la question 1 - chargement d'un fichier CSV
  - 2) Résolution de la question 2 - calcul de distance entre deux aéroports et matrice des distances
  - 3) Résolution de la question 3 - calcul des statistiques descriptives des aéroports
  - 4) Résolution de la question 4 - calcul des statistiques descriptives d'un sous-ensemble d'aéroports
  - 5) Résolution de la question 5 - calcul de la densité d'aéroports par rapport à la superficie d'un pays
  - 6) Résolution de la question 6 - projections d'aéroports sur une carte

  - 8) Afficher les commandes disponibles
  - 0) Quitter l'application
*****************************************
    
projet_top > 2
    +-----------
    | Question 2: calcul de distance entre deux aéroports et matrice des distances

    ID du premier aéroport: 525
    ID du second aéroport: 10
    Les aéroports utilisés pour la distance seront:
- Airport [
    airportId       721
    airportName     Sundsvall-Härnösand Airport
    cityName        Sundsvall
    countryName     Sweden
    latitude        62.528099060058594
    longitude       17.443899154663086
]
- Airport [
    airportId       1168
    airportName     Damazin Airport
    cityName        Damazin
    countryName     Sudan
    latitude        11.785900116
    longitude       34.3367004395
]
    Evaluation de la distance ...
    Evaluation effectuée.

    La distance entre ces deux aéroports est de: 5801.0145324074565 km.

    Génération de la DistanceMap ...
    Génération terminée ...

    DistanceMap résultante: 
    - AirportDistanceMap [
    pairs           812175
    min             2.376038071462652
    max             18843.06580566578
    avg             5306.832982877468
    median          5095.71084999169
    stdDev          3676.63900744707
]

projet_top > 
```

### Question 3

#### Implémentation
```scala

```
#### Utilisation via CLI 

```text

```

### Question 4

#### Implémentation
```scala

```
#### Utilisation via CLI 

```text

```

### Question 5

#### Implémentation
```scala

```
#### Utilisation via CLI 

```text

```
