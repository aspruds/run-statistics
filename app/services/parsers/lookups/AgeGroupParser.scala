package services.parsers.lookups

import models.id.CodeName
import services.parsers.utils.ParserUtils

object AgeGroupParser {
  def parse(html: String): Seq[CodeName] = {
    ParserUtils.parseOptions(html, "Vec.gr.:").map {
      case(code,name) => CodeName(code,name)
    }
  }
}
