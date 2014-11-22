package models.skriesim

import models.skriesim.id.IdName
import org.joda.time.LocalDate

case class Athlete(
                    id: Option[Long],
                    givenName: String,
                    familyName: String,
                    dateOfBirth: Option[LocalDate],
                    yearOfBirth: Option[Int],
                    ageGroup: String,
                    sex: String,
                    country: Option[String],
                    clubs: Seq[IdName],
                    coaches: Seq[IdName],
                    raceResults: Seq[RaceResult],
                    isCoach: Boolean)
