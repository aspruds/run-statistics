package models.statistics

import models.statistics.metadata.{WithRelation, WithMetadata}
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
                   updatedAt: Option[LocalDateTime],
                   updatedBy: Option[Long]) extends WithMetadata with WithRelation
