package services.parsers.lookups

import models.id.IdName
import services.parsers.utils.ParserUtils

object DisciplineTypesParser {
  def parse(html: String): Seq[IdName] = {
    ParserUtils.parseOptions(html, "Disciplīnu tips:").map {
      case(id,name) => IdName(id.toInt,name)
    }
  }
}
