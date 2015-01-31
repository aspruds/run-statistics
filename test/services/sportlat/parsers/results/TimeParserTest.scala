package services.sportlat.parsers.results

import org.joda.time.Duration
import org.specs2.mutable.Specification

class TimeParserTest extends Specification {
   "Time" should {

     "parse times with hours, minutes, seconds and millis" in {
       val expected = Duration.millis(65).
         plus(Duration.standardSeconds(49)).
         plus(Duration.standardMinutes(1)).
         getMillis

       TimeParser.parse("0:01:49,65") === Some(expected)
     }

     "parse times with hours, minutes, seconds and millis separated by dot" in {
       val expected = Duration.millis(5).
         plus(Duration.standardSeconds(33)).
         plus(Duration.standardMinutes(32)).
         plus(Duration.standardHours(1)).
         getMillis

       TimeParser.parse("01:32:33.05") === Some(expected)
     }

     "parse times with minutes, seconds and millis" in {
       val expected = Duration.millis(3).
         plus(Duration.standardSeconds(25)).
         plus(Duration.standardMinutes(1)).
         getMillis

       TimeParser.parse("01:25,3") === Some(expected)
     }

     "parse times with hours, minutes, seconds without millis" in {
       val expected = Duration.standardSeconds(38).
         plus(Duration.standardMinutes(3))
         .getMillis

       TimeParser.parse("00:03:38") === Some(expected)
     }

     "not parse DNF" in {
       TimeParser.parse("DNF") === None
       TimeParser.isDNF("DNF") must beTrue
       TimeParser.isDNF("DNS") must beFalse
     }

     "not parse DNS" in {
       TimeParser.parse("DNS") === None
       TimeParser.isDNS("DNS") must beTrue
       TimeParser.isDNF("DNS") must beFalse
     }
   }
 }
