package projet_top.projection

import projet_top.airports.Airport

sealed trait ProjectType
case object Equirectangular extends ProjectType
case object Mercator extends ProjectType

abstract class Projector(center: Int, project_type: ProjectType) {
  /**
    * Convertit la latitude et la longitude en coordonnées X,Y
    * @param candidate Aeroport a convertir
    * @return (X, Y)
    */
  def convertXY(candidate: Airport) : (Int, Int)

}
