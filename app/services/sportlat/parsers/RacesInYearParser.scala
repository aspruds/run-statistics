package services.sportlat.parsers

import models.sportlat.id.RaceId
import org.joda.time.format.DateTimeFormat
import org.jsoup.Jsoup
import utils.text.TextUtils._

import scala.collection.JavaConversions._

object RacesInYearParser {
  val DateFormat = "dd.MM.YYYY"

  def parse(html: String): Seq[RaceId] = {
    val doc = Jsoup.parse(html)

    val trs = doc.select("table.cal_table tr:has(td.cal_date)")

    trs.map {
      case(tr) =>

        def parseRaceId(): Option[Long] = {
          val resultsHref = tr.nextElementSibling().select("td a.cal_link:containsOwn(Rezultāti)").first

          Option(resultsHref).flatMap {
            case(resultsHref) =>

              val maybeId = resultsHref.attr("href")
                .replace("http://www.sportlat.lv/results.php?id=", "")
                .replace("http://www.sportlat.lv/result.php?id=", "")

              maybeId.toLongOption
          }
        }

        def parseName() = tr.select("td.cal_name").first.ownText

        def parseDate() = {
          val formatter = DateTimeFormat.forPattern(DateFormat)
          val text = tr.select("td.cal_date").first.ownText
          formatter.parseLocalDate(text)
        }

        def parseLocation() = tr.select("td.cal_place").first.ownText

        RaceId(
          id = parseRaceId(),
          name = parseName(),
          date = parseDate(),
          location = parseLocation()
        )
    }.toStream
  }
}
