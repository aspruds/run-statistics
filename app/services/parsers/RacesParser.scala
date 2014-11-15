package services.parsers

import models._
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import scala.collection.JavaConversions._

object RacesParser {
  def parse(html: String): Seq[RaceId] = {
    val doc = Jsoup.parse(html)
    val options = doc.select(":containsOwn(Sacensības nosaukums:) + div.data select option")

    options.filterNot(_.attr("value").isEmpty).map {
      option => {
        val id = option.attr("value").toInt
        val name = option.ownText
        RaceId(id,name)
      }
    }
  }
}
