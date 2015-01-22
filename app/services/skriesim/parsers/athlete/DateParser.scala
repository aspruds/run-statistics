package services.skriesim.parsers.athlete

import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat
import utils.text.{TextUtils, HtmlConstants}

class DateParser(dateString: String) {
  val DatePattern = "YYYY.MM.dd"

  val datePart = {
    TextUtils.toOption(dateString).map(_.split(HtmlConstants.NbspUnicode).head)
  }

  def fullDate(part: String): Option[String] = {
    if (part.length == 10)
      Some(part)
    else
      None
  }

  def parseDateOfBirth(): Option[LocalDate] = {
    datePart.flatMap(fullDate(_)).map {
      date => LocalDate.parse(date, DateTimeFormat.forPattern(DatePattern))
    }
  }

  def parseYearOfBirth(): Option[Int] = {
    datePart.flatMap(fullDate(_)) match {
      case Some(_) => Some(parseDateOfBirth().get.getYear)
      case None => datePart map (_.toInt)
    }
  }
}