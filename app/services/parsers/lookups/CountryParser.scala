package services.parsers.lookups

import models.id.CodeName
import services.parsers.utils.ParserUtils

object CountryParser {
  def parse(html: String): Seq[CodeName] = {
    ParserUtils.parseOptions(html, "Sportista valsts:").map {
      case(code,name) => CodeName(code,name)
    }
  }
}
