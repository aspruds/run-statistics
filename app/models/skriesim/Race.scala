package models.skriesim

import org.joda.time.LocalDate

case class Race(
                 id: Option[Long],
                 name: String,
                 date: LocalDate,
                 countryCode: Option[String],
                 url: Option[String]
                 )
