package services.parsers.lookups

import models.id.IdName
import services.parsers.utils.ParserUtils

object RacesParser {
  def parse(html: String): Seq[IdName] = {
    ParserUtils.parseOptions(html, "Sacensības nosaukums:").map {
      case(id,name) => IdName(id.toInt,name)
    }
  }
}
