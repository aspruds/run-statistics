package services.skriesim.parsers.lookups

import models.skriesim.id.CodeName
import services.skriesim.parsers.utils.ParserUtils

object CountryParser {
  def parse(html: String): Seq[CodeName] = {
    ParserUtils.parseOptions(html, "Sportista valsts:").map {
      case(code,name) => CodeName(code,name)
    }
  }

  def parseEnglish(html: String): Seq[CodeName] = {
    ParserUtils.parseOptions(html, "Competition country:").map {
      case(code,name) => CodeName(code,name)
    }
  }
}
