package services.parsers.lookups

import models.id.IdName
import services.parsers.utils.ParserUtils

object CoachesParser {
  def parse(html: String): Seq[IdName] = {
    ParserUtils.parsePersonList(html, "http://skriesim.lv/athletes?id=")
  }
}
