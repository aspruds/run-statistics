package services.skriesim.parsers

import _root_.utils.text.TextUtils
import models.skriesim.Race
import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat
import org.jsoup.Jsoup

import scala.collection.JavaConversions._

object RaceParser {
  def parse(html: String): Race = {
    val DatePattern = "YYYY.MM.dd"

    val doc = Jsoup.parse(html)
    val container = doc.select("div.calendarView div.title").first

    // replace &nbsp;
    val containerText = container.ownText.replace(TextUtils.nbsp, "").trim

    val date = {
      val dateAsText = containerText.take(10)
      LocalDate.parse(dateAsText, DateTimeFormat.forPattern(DatePattern))
    }

    val name = containerText.drop(13).dropRight(4)

    val countryCode = {
      val location = {
        val parts = containerText.split("-")
        if (parts.length < 3)
          None
        else
          TextUtils.toOption(parts.last)
      }

      location.flatMap {
        loc =>
          if (loc.length >= 2) {
            Some(loc.takeRight(2))
          }
          else {
            None
          }
      }
    }

    val url = container.select("a").headOption.map(_.attr("href"))

    Race(None, name, date, countryCode, url)
  }
}
