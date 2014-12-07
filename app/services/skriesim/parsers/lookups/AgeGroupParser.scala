package services.skriesim.parsers.lookups

import models.skriesim.id.CodeName
import services.skriesim.parsers.utils.ParserUtils

object AgeGroupParser {
  def parse(html: String): Seq[CodeName] = {
    ParserUtils.parseOptions(html, "Vec.gr.:").map {
      case (code, name) => CodeName(code, name)
    }
  }
}
