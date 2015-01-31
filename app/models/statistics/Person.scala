package models.statistics

import models.statistics.metadata.{WithMetadata, WithRelation}
import org.joda.time.{LocalDate, LocalDateTime}

case class Person(
                   id: Long,
                   givenName: String,
                   familyName: String,
                   dateOfBirth: Option[LocalDate],
                   yearOfBirth: Option[Int],
                   sex: Option[String],
                   countryId: Option[Long],
                   skriesimId: Option[Long],
                   sportlatId: Option[Long],
                   noskrienId: Option[Long],
                   isCoach: Boolean,
                   updatedAt: Option[LocalDateTime],
                   updatedBy: Option[Long]) extends WithMetadata with WithRelation
