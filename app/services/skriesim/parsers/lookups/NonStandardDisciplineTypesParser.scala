package services.skriesim.parsers.lookups

import models.skriesim.NonStandardDistance
import services.skriesim.parsers.utils.ParserUtils

object NonStandardDisciplineTypesParser {
  def parse(html: String): Seq[NonStandardDistance] = {
    ParserUtils.parseOptions(html, "Nestandartas disciplīnas:").map {
      case (code, name) => NonStandardDistance(code, name, BigDecimal(code))
    }
  }
}
