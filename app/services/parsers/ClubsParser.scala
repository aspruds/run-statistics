package services.parsers

import models._
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import scala.collection.JavaConversions._

object ClubsParser {
  def parse(html: String): Seq[ClubId] = {
    val doc = Jsoup.parse(html)
    val hrefs = doc.select("table.personList td a")
    hrefs.map {
      href => {
        val id = href.attr("href").replace("http://skriesim.lv/clubs?id=", "").toInt
        val name = href.ownText()
        ClubId(id,name)
      }
    }
  }
}
