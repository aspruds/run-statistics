package models.statistics

import org.joda.time.LocalDateTime

case class PersonClub(
                   id: Long,
                   personId: Long,
                   clubId: Long,
                   createdAt: LocalDateTime,
                   updateAt: LocalDateTime,
                   updatedById: Option[Long])
