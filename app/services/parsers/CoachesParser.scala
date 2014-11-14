package services.parsers

import models._
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import scala.collection.JavaConversions._

object CoachesParser {
  def parse(html: String): Seq[CoachId] = {
    val doc = Jsoup.parse(html)
    val hrefs = doc.select("table.personList td a")
    hrefs.map {
      href => {
        val id = href.attr("href").replace("http://skriesim.lv/athletes?id=", "").toInt
        val name = href.ownText()
        CoachId(id,name)
      }
    }
  }
}
