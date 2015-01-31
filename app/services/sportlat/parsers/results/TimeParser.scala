package services.sportlat.parsers.results

import org.joda.time.Duration

object TimeParser {
  val MillisecondDelimiters = List(",",".")

  def isTime(text: String) = text.contains(":")

  def isDNS(text: String) = text == "DNS"

  def isDNF(text: String) = text == "DNF"

  def parse(text: String): Option[Long] = {
     if(isTime(text)) {
       def hasMilliseconds = MillisecondDelimiters.exists(text.contains(_))

       val timeText = {
         if (hasMilliseconds)
           text
         else text + ":00"
       }

       val parts = timeText.split(":|\\.|,").map(_.toInt).toList
       val durations: List[Int] = parts.size match {
         case 2 => List(0,0,0) ::: parts
         case 3 => List(0,0) ::: parts
         case 4 => List(0) ::: parts
         case 5 => parts
         case _ => throw new Exception(s"unable to parse duration: $text")
       }

       val duration = Duration.standardHours(durations(0))
         .plus(Duration.standardHours(durations(1)))
         .plus(Duration.standardMinutes(durations(2)))
         .plus(Duration.standardSeconds(durations(3)))
         .plus(new Duration(durations(4)))

       Some(duration.getMillis)
     }
     else None
   }
 }
