package services.skriesim.parsers.lookups

import models.skriesim.id.IdName
import org.jsoup.Jsoup

import scala.collection.JavaConversions._

object AthletesParser {
  def parse(html: String): Seq[IdName] = {
    val doc = Jsoup.parse(html)
    val hrefs = doc.select("table.personList td a")
    hrefs.map {
      href => {
        val id = href.attr("href").replace("http://skriesim.lv/athletes?id=", "").toInt
        val name = href.ownText()
        IdName(id,name)
      }
    }
  }
}
