package projet_top

import java.util.Base64

object Utils {
  def encodeBase64(data: String): String = {
    Base64.getEncoder.encodeToString(data.getBytes("ASCII"))
  }
}
