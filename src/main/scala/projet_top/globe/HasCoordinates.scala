package projet_top.globe

/**
  * Pour un objet qui possède une longitude et une longitude
  */
trait HasCoordinates {
  /**
    * Latitude en degrés. Doit être dans la plage [-90, 90[°.
    */
  val latitude: Double

  /**
    * Longitude en degrés. Doit être dans la plage [-180, 180[°.
    */
  val longitude: Double

  require(
    -90 <= this.latitude && this.latitude < 90,
    s"latitude must be in range [-90, 90[° (found: ${this.latitude})"
  )
  require(
    -180 <= this.longitude && this.longitude < 180,
    s"longitude must be in range [-180, 180[° (found: ${this.longitude})"
  )

  /**
    * Creates a point with the same coordinates as this object.
    * @return a point with the same coordinates as this object
    */
  def toPoint: Point = {
    Point(this.latitude, this.longitude)
  }
}
