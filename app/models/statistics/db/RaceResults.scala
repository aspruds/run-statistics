package models.statistics.db

import models.statistics.RaceResult
import models.statistics.metadata.db.WithMetadatas
import play.api.db.slick.Config.driver.simple._

class RaceResults(tag: Tag) extends Table[RaceResult](tag, "race_results")
with WithMetadatas[RaceResult] {

  def raceId = column[Long]("race_id")

  def raceDistanceId = column[Long]("race_distance_id")

  def personId = column[Long]("person_id")

  def time = column[Option[Long]]("time")

  def distance = column[Option[BigDecimal]]("distance")

  def points = column[Option[Short]]("points")

  def rank = column[Option[Int]]("rank")

  def rankText = column[Option[String]]("rank_text")

  def ageGroupId = column[Option[Long]]("age_group_id")

  def classificationTypeId = column[Option[Long]]("classification_type_id")

  def wind = column[Option[BigDecimal]]("wind")

  def * = (
    id,
    raceId,
    raceDistanceId,
    personId,
    time,
    distance,
    points,
    rank,
    rankText,
    ageGroupId,
    classificationTypeId,
    wind,
    updatedAt,
    updatedBy) <>(RaceResult.tupled, RaceResult.unapply)
}