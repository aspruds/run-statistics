package services.parsers.lookups

import models.id.IdName
import services.parsers.utils.ParserUtils

object ClubsParser {
  def parse(html: String): Seq[IdName] = {
    ParserUtils.parsePersonList(html, "http://skriesim.lv/clubs?id=")
  }
}
