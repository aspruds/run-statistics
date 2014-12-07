package models.statistics

import models.statistics.metadata.{WithMetadata, WithName}
import org.joda.time.LocalDateTime

case class DistanceType(
                         id: Long,
                         name: String,
                         skriesimName: Option[String],
                         distance: Option[BigDecimal],
                         weight: Option[BigDecimal],
                         height: Option[BigDecimal],
                         disciplineTypeId: Option[Long],
                         isStandard: Option[Boolean],
                         updatedAt: Option[LocalDateTime],
                         updatedBy: Option[Long]) extends WithMetadata with WithName
