package models.statistics.db

import models.statistics.RaceDistance
import models.statistics.metadata.db.{WithMetadatas, WithNames}
import play.api.db.slick.Config.driver.simple._

class RaceDistances(tag: Tag) extends Table[RaceDistance](tag, "race_distances")
 with WithMetadatas[RaceDistance] with WithNames[RaceDistance] {

  def raceId = column[Long]("race_id")

  def distanceTypeId = column[Option[Long]]("distance_type_id")

  def isCertified = column[Option[Boolean]]("is_certified")

  def isElectronicTime = column[Option[Boolean]]("is_electronic_time")

   def * = (
     id,
     raceId,
     distanceTypeId,
     name,
     isCertified,
     isElectronicTime,
     updatedAt,
     updatedBy) <> (RaceDistance.tupled, RaceDistance.unapply)
 }