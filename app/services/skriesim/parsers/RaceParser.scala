package services.skriesim.parsers

import _root_.utils.text.TextUtils
import models.skriesim.Race
import org.jsoup.Jsoup

import scala.collection.JavaConversions._

object RaceParser {
  def parse(html: String): Race = {
    val doc = Jsoup.parse(html)
    val container = doc.select("div.calendarView div.title").first

    // replace &nbsp;
    val containerText = container.ownText.replace(TextUtils.nbsp, "").trim

    val date = containerText.take(10)

    val name = containerText.drop(13).dropRight(4)

    val countryCode = containerText.takeRight(2)

    val url = container.select("a").headOption.map(_.attr("href"))

    Race(None, date, name, countryCode, url)
  }
}
