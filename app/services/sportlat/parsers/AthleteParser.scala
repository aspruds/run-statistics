package services.sportlat.parsers

import java.util.Locale

import models.sportlat.Athlete
import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import utils.text.HtmlConstants

import scala.collection.JavaConversions._

object AthleteParser {
  val DatePattern = "YYYY.d.MMMM"

  def parse(html: String): Option[Athlete] = {
    val doc = Jsoup.parse(html)

    val maybeName = Option(doc.select("h2.vards").first).map(_.ownText)
    maybeName.map {
      maybeName => parseAthlete(doc, maybeName)
    }
  }

  def parseAthlete(doc: Document, maybeName: String): Athlete = {
    val nameParts = maybeName.split(" ")

    val parsedElements = {
      doc.select("td.user_data b").map {
        elem =>
          val id = elem.ownText.replace(":", "")
          val data = elem.nextSibling().toString.trim

          (id, data)
      }.toMap
    }

    def parseGivenName(): String = {
      nameParts.dropRight(1).mkString(" ")
    }

    def parseFamilyName(): String = {
      nameParts.takeRight(1).mkString(" ")
    }

    def parseCity() = parsedElements.get("Pilsēta")

    def parseCountry() = parsedElements.get("Valsts")

    def parseClub() = parsedElements.get("Komanda").map {
      _
        .replace(HtmlConstants.Quot, "\"")
        .replace(HtmlConstants.Amp, "&")
        .replace(HtmlConstants.Nbsp, " ")
        .replace("\\'\\'", "\"")
    }

    val birthdayText = parsedElements.get("Dzimšanas diena")

    def parseDateOfBirth() = birthdayText.map {
      text =>
        val dateText = text
          .replace(" gada ", "")
          .replace(" ", "")
          .toLowerCase

        val locale = Locale.forLanguageTag("lv")

        val format = DateTimeFormat.forPattern(DatePattern).withLocale(locale)

        LocalDate.parse(dateText, format)
    }

    Athlete(
      id = None,
      givenName = parseGivenName(),
      familyName = parseFamilyName(),
      city = parseCity(),
      country = parseCountry(),
      club = parseClub(),
      dateOfBirth = parseDateOfBirth()
    )
  }
}
