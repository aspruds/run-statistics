package services.skriesim.parsers

import _root_.utils.text.TextUtils._
import models.skriesim.Club
import org.jsoup.Jsoup

object ClubParser {
  def parse(html: String): Club = {
    val doc = Jsoup.parse(html)
    val container = doc.select("table.personView").first
    val nameTD = container.select("td.name").first

    def getOptionFromElement(selector: String) = {
      container.select(selector).first.ownText.trim.toOption
    }

    def parseName() = nameTD.ownText

    def parseCountry() = {
      val countryWithBraces = nameTD.select(".disciplineDetails").first.ownText
      countryWithBraces.replaceAll("\\(|\\)", "").toOption
    }

    def parseTitle() = {
      getOptionFromElement("div.title")
    }

    def parseDescription() = {
      getOptionFromElement("div.text")
    }

    def parseFullDescription() = {
      container.select("div.text").first.html().trim.toOption
    }

    Club(None, parseName(), parseCountry(), parseTitle(), parseDescription(), parseFullDescription())
  }
}
