package models.skriesim.results

import play.Logger

object Distance {
   def isDistance(text: String) = text.endsWith("m") || !text.contains(".")

  def parse(text: String): Option[BigDecimal] = {
    if(isDistance(text)) {
      val distance = text.replace("m", "")
      Some(BigDecimal(distance))
    }
    else None
  }
}
