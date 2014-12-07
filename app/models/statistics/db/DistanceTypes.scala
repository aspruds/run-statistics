package models.statistics.db

import models.statistics.DistanceType
import models.statistics.metadata.db.{WithMetadatas, WithNames}
import play.api.db.slick.Config.driver.simple._

class DistanceTypes(tag: Tag) extends Table[DistanceType](tag, "distance_types")
with WithMetadatas[DistanceType] with WithNames[DistanceType] {

  def skriesimName = column[Option[String]]("skriesim_name")

  def distance = column[Option[BigDecimal]]("distance")

  def weight = column[Option[BigDecimal]]("weight")

  def height = column[Option[BigDecimal]]("height")

  def disciplineTypeId = column[Option[Long]]("discipline_type_id")

  def isStandard = column[Option[Boolean]]("is_standard")

  def * = (
    id,
    name,
    skriesimName,
    distance,
    weight,
    height,
    disciplineTypeId,
    isStandard,
    updatedAt,
    updatedBy) <>(DistanceType.tupled, DistanceType.unapply)
}