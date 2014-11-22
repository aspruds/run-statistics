package models.statistics

import org.joda.time.{LocalDateTime, LocalDate}

case class Person(
                   id: Long,
                   givenName: String,
                   familyName: String,
                   dateOfBirth: Option[LocalDate],
                   yearOfBirth: Option[Int],
                   sex: String,
                   countryId: Option[Long],
                   skriesimId: Option[Long],
                   sportlatId: Option[Long],
                   noskrienId: Option[Long],
                   isCoach: Boolean,
                   createdAt: LocalDateTime,
                   updateAt: LocalDateTime,
                   updatedById: Option[Long])
