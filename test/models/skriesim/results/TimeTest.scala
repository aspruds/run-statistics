package models.skriesim.results

import org.joda.time.Duration
import org.specs2.mutable.Specification

class TimeTest extends Specification {
  "Time" should {
    "parse times with days, hours, minutes, seconds and millis" in {
      val expected = Duration.standardSeconds(7).
        plus(Duration.standardMinutes(37)).
        plus(Duration.standardHours(5)).
        plus(Duration.standardDays(1)).
        getMillis

      Time.parse("1:05:37:07.0") === Some(expected)
    }

    "parse times with hours, minutes, seconds and millis" in {
      val expected = Duration.standardSeconds(53).
        plus(Duration.standardMinutes(1)).
        plus(Duration.standardHours(8)).
        getMillis

      Time.parse("08:01:53.0") === Some(expected)
    }

    "parse times with minutes, seconds and millis" in {
      val expected = Duration.standardSeconds(53).
        plus(Duration.standardMinutes(1)).
        getMillis

      Time.parse("01:53.0") === Some(expected)
    }

    "parse times with seconds and millis" in {
      val expected = new Duration(66).plus(Duration.standardSeconds(10)).getMillis
      Time.parse("10.66") === Some(expected)
    }
  }
}
