package services.skriesim.parsers.lookups

import models.skriesim.id.IdName
import services.skriesim.parsers.utils.ParserUtils

object DisciplineTypesParser {
  def parse(html: String): Seq[IdName] = {
    ParserUtils.parseOptions(html, "Disciplīnu tips:").map {
      case (id, name) => IdName(id.toLong, name)
    }
  }
}
