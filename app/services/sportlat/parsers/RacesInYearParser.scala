package services.sportlat.parsers

import models.sportlat.id.RaceId
import org.jsoup.Jsoup
import play.Logger

import scala.collection.JavaConversions._

object RacesInYearParser {

  def parse(html: String): Seq[RaceId] = {
    val doc = Jsoup.parse(html)

    val trs = doc.select("table.cal_table tr:has(td.cal_date)")

    trs.map {
      tr => {
        val name = tr.select("td.cal_name").first.ownText
        val date = tr.select("td.cal_date").first.ownText
        val location = tr.select("td.cal_place").first.ownText

        def parseRaceId: Option[Long] = {
          val resultsHref = tr.nextElementSibling().select("td a.cal_link:containsOwn(Rezultāti)").first

          Option(resultsHref).flatMap {
            resultsHref =>

              def optionalDigit(str: String): Option[Long] = {
                if(str.forall(_.isDigit))
                  Some(str.toLong)
                else
                  None
              }

              val maybeId = resultsHref.attr("href")
                .replace("http://www.sportlat.lv/results.php?id=", "")
                .replace("http://www.sportlat.lv/result.php?id=", "")

              optionalDigit(maybeId)
          }
        }

        RaceId(
          id = parseRaceId,
          name = name,
          date = date,
          location = location
        )
      }
    }.toStream
  }
}
