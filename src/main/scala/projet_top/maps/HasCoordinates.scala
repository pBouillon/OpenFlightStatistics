package projet_top.maps

/**
  * Pour un objet qui possède une latitude et une longitude
  */
trait HasCoordinates {
  /**
    * Latitude en degrés. Doit être dans la plage ]-90, 90]°.
    */
  val latitude: Double

  /**
    * Longitude en degrés. Doit être dans la plage ]-180, 180]°.
    */
  val longitude: Double

  require(
    -90 < this.latitude && this.latitude <= 90,
    "latitude must be in range ]-90, 90]°"
  )
  require(
    -180 < this.longitude && this.longitude <= 180,
    "longitude must be in range ]-180, 180]°"
  )

  /**
    * Creates a point with the same coordinates as this object.
    * @return a point with the same coordinates as this object
    */
  def toPoint: Point = {
    Point(this.latitude, this.longitude)
  }
}
