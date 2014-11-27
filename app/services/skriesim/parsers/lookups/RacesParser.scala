package services.skriesim.parsers.lookups

import models.skriesim.id.IdName
import services.skriesim.parsers.utils.ParserUtils

object RacesParser {
  def parse(html: String): Seq[IdName] = {
    ParserUtils.parseOptions(html, "Sacensības nosaukums:").map {
      case(id,name) => IdName(id.toLong,name)
    }.toStream
  }
}
