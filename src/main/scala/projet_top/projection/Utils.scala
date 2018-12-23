package projet_top.projection

object Utils {
  def normLatitude(originalLatitude: Double): Double = {
    var latitude = originalLatitude
    while (latitude < -90.0) { latitude += 180.0 }
    while (latitude >= 90.0) { latitude -= 180.0 }
    latitude
  }

  def normLongitude(originalLongitude: Double): Double = {
    var longitude = originalLongitude
    while (longitude < -180.0) { longitude += 360.0 }
    while (longitude >= 180.0) { longitude -= 360.0 }
    longitude
  }

  def centerWHToULWH(xCenter: Double, yCenter: Double, width: Double, height: Double): (Double, Double, Double, Double) = {
    val xUpperLeft = xCenter - width / 2.0
    val yUpperLeft = yCenter - height / 2.0
    (xUpperLeft, yUpperLeft, width, height)
  }

  def centerSizeToULWH(xCenter: Double, yCenter: Double, size: Double): (Double, Double, Double, Double) = {
    val xUpperLeft = xCenter - size / 2.0
    val yUpperLeft = yCenter - size / 2.0
    (xUpperLeft, yUpperLeft, size, size)
  }
}
