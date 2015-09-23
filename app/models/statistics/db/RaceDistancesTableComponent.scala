package models.statistics.db

import models.statistics.RaceDistance
import models.statistics.metadata.db.{WithMetadatasComponent, WithNamesComponent}
import play.api.db.slick.HasDatabaseConfig
import slick.driver.JdbcProfile

trait RaceDistancesTableComponent extends WithMetadatasComponent with WithNamesComponent {
  self: HasDatabaseConfig[JdbcProfile] =>

  import driver.api._
  import utils.db.PortableJodaSupport._

  class RaceDistancesTable(tag: Tag) extends Table[RaceDistance](tag, "race_distances")
  with WithMetadatas[RaceDistance] {

    def raceId = column[Long]("race_id")

    def distanceTypeId = column[Option[Long]]("distance_type_id")

    def withQualification = column[Option[Boolean]]("with_qualification")

    def distance = column[Option[BigDecimal]]("distance")

    def venueTypeId = column[Option[Long]]("venue_type_id")

    def isCertified = column[Option[Boolean]]("is_certified")

    def isElectronicTime = column[Option[Boolean]]("is_electronic_time")

    def * = (
      id,
      raceId,
      distanceTypeId,
      withQualification,
      venueTypeId,
      isCertified,
      isElectronicTime,
      updatedAt,
      updatedBy) <>(RaceDistance.tupled, RaceDistance.unapply)
  }

}