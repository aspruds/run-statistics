package services.skriesim.parsers.results

import org.joda.time.Duration

object TimeParser {
  def isTime(text: String) = text.contains(".") && !text.endsWith("m")

  def parse(text: String): Option[Long] = {
    if(isTime(text)) {
      val parts = text.split(":|\\.").map(_.toInt).toList
      val durations: List[Int] = parts.size match {
        case 2 => List(0,0,0) ::: parts
        case 3 => List(0,0) ::: parts
        case 4 => List(0) ::: parts
        case 5 => parts
        case _ => throw new Exception(s"unable to parse duration: $text")
      }

      val duration = Duration.standardDays(durations(0))
        .plus(Duration.standardHours(durations(1)))
        .plus(Duration.standardMinutes(durations(2)))
        .plus(Duration.standardSeconds(durations(3)))
        .plus(new Duration(durations(4)))

      Some(duration.getMillis)
    }
    else None
  }
}
