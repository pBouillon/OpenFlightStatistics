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

### Prémices - CLI 

Pour un usage facilité de notre logiciel, nous avons mis en place une interface simplifiée, en mode console, pour l'utiliser. Il s'agit d'un petit outil console permettant de naviguer entre les options et de choisir celle(s) souhaitée(s) par l'utilisateur.  

Il est à noter que les valeurs affichées pour les extraits suivants dépendent de la base par défaut. Ces valeurs peuvent donc varier si la base est différente ou certaines valeurs altérées.

#### Affichage initial

Ainsi, par défaut notre `Main` affiche cet écran d'accueil

```text

*****************************************
  Projet TOP - Manipulations d'openflight

   Auteurs:
  - BAGREL Thomas
  - BOUILLON Pierre
  - THOUVENIN Axel
  - VOGT Florian
*****************************************
    
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
    
projet_top > 
```

_Note:_ Par défaut, comme dans l'exemple ci-dessus, on chargera la base de données réduite, mais il est tout a fait possible de spécifier un chemin vers un autre fichier `.csv` pour d'autres données.

_Remarque:_ Pour la suite des exemples, cette en-tête sera omise, par soucis de clarté.

#### Aide

Il est possible d'afficher l'aide de l'application, via la commande proposée au début (initialement, `8`):

```text
projet_top > 8

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
    
projet_top > 
```

#### Fermeture

Quitter l'application se fait par la commande `0`:

```text
projet_top > 0
Fermeture de l'application ...

Process finished with exit code 0
```

### Question 1 - chargement d'une base

#### Implémentation

```scala
// chargement
val base: AirportDatabase = AirportDatabase.loadFromCSV(new File("path_to_my_base.csv"))

// vérification
println(s"Nombre d'aéroports chargés depuis la base: ${base.toList.length}")
```

#### Utilisation via CLI

```text
projet_top > 1
    +-----------
    | Question 1: affichage du contenu du fichier CSV

    Votre base contient 1275 aéroport(s), les afficher ? (o/N): N

projet_top > 
```

### Question 2 - calcul de distance entre deux aéroports et matrice des distances

#### Implémentation

```scala
// base d'aéroports
val base: AirportDatabase = ...

// aéroports utilisés
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
projet_top > 2
    +-----------
    | Question 2: calcul de distance entre deux aéroports et matrice des distances

    ID du premier aéroport: 525
    ID du second aéroport: 42
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
    airportId       170
    airportName     Pangnirtung Airport
    cityName        Pangnirtung
    countryName     Canada
    latitude        66.1449966431
    longitude       -65.71360015869999
]
    Evaluation de la distance ...
    Evaluation effectuée.

    La distance entre ces deux aéroports est de: 3727.5291193472995 km.

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

### Question 3 - calcul des statistiques descriptives des aéroports

#### Implémentation

```scala
// base d'aéroports
val base: AirportDatabase = ...

// création des statistiques dans une getDistanceMap
val matrice = base.getDistanceMap

// récupération des statistiques
val distMinimale = matrice.minDistance
val distMaximale = matrice.maxDistance
val distMoyenne  = matrice.avgDistance
val distMediane  = matrice.medianDistance
val ecartType    = matrice.stdDev
```

#### Utilisation via CLI 

```text
projet_top > 3
    +-----------
    | Question 3: calcul des statistiques descriptives des aéroports

    Génération de la DistanceMap ...
    Génération terminée.

    Calculs des propriétés ...
    Calculs effectués.

    - Distance minimale: 2.376038071462652
    - Distance maximale: 18843.06580566578
    - Distance moyenne: 5306.832982877468
    - Distance médiane: 5095.71084999169
    - Ecart-type: 3676.63900744707

projet_top > 
```

### Question 4 - calcul des statistiques descriptives d'un sous-ensemble d'aéroports

#### Implémentation

```scala
// base d'aéroports
val base: AirportDatabase = ...

// sélections
val hemisphereSelectionne: HemisphereChoice = Northern
val paysSelectionnes: List[String] = List("Canada", "France")

base.getSubset(CountryNames(paysSelectionnes) || Hemisphere(hemisphereSelectionne))
```

#### Utilisation via CLI 

```text
projet_top > 4
    +-----------
    | Question 4: calcul des statistiques descriptives d'un sous-ensemble d'aéroports

    Représenter tous les aéroports de tous les pays présents en base ?(o/N): N
    Exclure un hémisphère ?(o/N): N
    N'afficher que certains pays ?(o/N): o
    Entrez les pays à afficher avec les même noms que dans la base, séparés par un espace:
    projet_top > France Canada
    Votre base réduite contient 241 aéroport(s), les afficher ? (o/N): N

    DistanceMap de la base réduite: AirportDistanceMap [
    pairs           28920
    min             7.105918988870007
    max             8560.364305749717
    avg             3560.4171256971013
    median          3103.725631176765
    stdDev          2401.426673463976
]

projet_top >
```

### Question 5 - calcul de la densité d'aéroports par rapport à la superficie d'un pays

#### Implémentation

```scala
// base d'aéroports
val base: AirportDatabase = ...

// base de pays
val countryBase: CountryDatabase = CountryDatabase.loadFromCSV(new File("path_to_data.csv"))

// choix
val paysEvalue: String = "France"

// résultat
val density = base.getDensityIn(countryBase.getCountryByName(countryName), _.inhabitants)
```

#### Utilisation via CLI 

```text
projet_top > 5
    +-----------
    | Question 5: calcul de la densité d'aéroports par rapport à la superficie d'un pays

    Voulez vous charger le fichier de pays par défaut ? (o/N): o
    Chargement ...
    Chargement effectué !

    Entrez le nom du pays (tel que présent en base) sur lequel faire ces statistiques
    projet_top > Canada

    Calcul de densité ...
    Calcul effectué.

    Densité pour le pays choisi (Canada): 5.116789050580345E-6 aéoroport(s)/km²

projet_top > 
```

### Questions 6 et 7 - projections d'aéroports sur une carte

#### Implémentation

```scala
// base d'aéroports
val base: AirportDatabase = ...

// --- valeurs necessaires à la création de la projections
val serveurDeProjection 
    = new D3BackMapProvider("http://d3backmapprovider.westeurope.azurecontainer.io:8080/")
// largeur en px; la longueur est automatiquement calculée en conséquence
val largeur = 4000
    
// marqueurs
val tailleMarqueur = 20
val couleurMarqueur = Color.red
// Note: 
//    Round hérite de Marker
//    il est donc facile de changer la forme avec le polymorphisme
//    comme dans le CLI
val marqueur = markers.Round(couleurMarqueur, markers.Filled)(tailleMarqueur)

// centre de la projection
val airportACentrer = ...
// Note: 
//    pour ne pas centrer, un objet Point de coordonnées (0, 0) suffit
//    pas besoin d'un nouvel objet Airport 
val centre: HasCoordinates = airportACentrer

// --- creation de la projection
// Note:
//    là encore, les projections héritent de Projector
//    on peut donc facilement moduler le type de projection
val typeProjection = new EquiRectangularLat0Projector(centre)
val generateurCarte 
    = new MapCreator(typeProjection, serveurDeProjection)(largeur)

// --- ajout sur la projection des aéroports en base
// on peut aussi utiliser une base réduite si désiré, comme dans le CLI
generateurCarte.plotAll(base)(marqueur)

// --- sauvegarde vers un fichier
val nomDestination: String = "out"
mapCreator.saveToFile(new File(nomDestination + ".png"))
```

#### Utilisation via CLI 

```text
projet_top > 6
    +-----------
    | Question 6: projections d'aéroports sur une carte

    Voulez vous utiliser la largeur par défaut (4000 px) ? (o/N): o

    Quel type de projection voulez-vous ?
    - 1) Equirectangulaire centrée seulement sur la longitude de l'aéroport
    - 2) Equirectangulaire centrée la latitude et la longitude de l'aéroport
    projet_top > 2
    Id de l'aéroport à utiliser comme centre (0 pour ne pas en sélectionner): 525
    Aéorport choisi: Airport [
    airportId       525
    airportName     RAF Ternhill
    cityName        Ternhill
    countryName     United Kingdom
    latitude        52.87120056152344
    longitude       -2.533560037612915
]

    Utiliser un marqueur plein ?(o/N): N
    Quel type de marker voulez-vous ?
    - 1) Rectangulaire
    - 2) Rond
    - 3) Carré
    projet_top > 2

    Représenter tous les aéroports de tous les pays présents en base ?(o/N): N
    Exclure un hémisphère ?(o/N): o
    Quel hémisphère exclure ?(Nord = o / Sud = N): o
    N'afficher que certains pays ?(o/N): N

    Entrer le nom du fichier destination: out
    Carte générée dans out.png

projet_top > 
```
