package services.skriesim.parsers.lookups

import models.skriesim.id.IdName
import services.skriesim.parsers.utils.ParserUtils

object ClubsParser {
  def parse(html: String): Seq[IdName] = {
    ParserUtils.parsePersonList(html, "http://skriesim.lv/clubs?id=")
  }
}
