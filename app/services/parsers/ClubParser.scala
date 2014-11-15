package services.parsers

import models._
import org.jsoup.Jsoup
import utils.TextUtils

object ClubParser {
  def parse(html: String): Club = {
    val doc = Jsoup.parse(html)
    val container = doc.select("table.personView").first
    val nameTD = container.select("td.name").first

    def getOptionFromElement(selector: String) = {
      val text  = container.select(selector).first.ownText.trim
      TextUtils.toOption(text)
    }

    val name = nameTD.ownText

    val country = {
      val countryWithBraces = nameTD.select(".disciplineDetails").first.ownText
      countryWithBraces.replaceAll("\\(|\\)", "")
    }

    val title = getOptionFromElement("div.title")

    val description = getOptionFromElement("div.text")

    val fullDescription = {
      val desc = container.select("div.text").first.html().trim
      TextUtils.toOption(desc)
    }

    Club(None, name, country, title, description, fullDescription)
  }
}
