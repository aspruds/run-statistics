package models.sportlat

import org.joda.time.LocalDate

case class Athlete(
                    id: Option[Long],
                    givenName: String,
                    familyName: String,
                    city: Option[String],
                    country: Option[String],
                    club: Option[String],
                    dateOfBirth: Option[LocalDate])
