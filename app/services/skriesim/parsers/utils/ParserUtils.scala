package services.skriesim.parsers.utils

import models.skriesim.id.IdName
import org.jsoup.Jsoup

import scala.collection.JavaConversions._

object ParserUtils {

  def parseOptions(html: String, title: String) = {
    val doc = Jsoup.parse(html)
    val options = doc.select(s":matchesOwn($title) + div.data select option")
    options.filterNot(_.attr("value").isEmpty).map {
      option => {
        val id = option.attr("value")
        val name = option.ownText
        (id, name)
      }
    }.toStream
  }

  def parsePersonList(html: String, url: String): Seq[IdName] = {
    val doc = Jsoup.parse(html)
    val hrefs = doc.select("table.personList td a")
    hrefs.map {
      href => {
        val id = href.attr("href").replace(url, "").toLong
        val name = href.ownText()
        IdName(id, name)
      }
    }.toStream
  }

}
