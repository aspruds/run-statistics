package services.statistics.db

import models.statistics.Race
import models.statistics.db.Races
import play.Logger
import services.statistics.db.support.{CRUDRepository, DefaultCRUDRepository}

trait RaceRepositoryComponent {

  val raceRepository: RaceRepository

  import play.api.db.slick.Config.driver.simple._

  class DefaultRaceRepository extends DefaultCRUDRepository[Race, Races] with RaceRepository {
    override val tableReference = TableQuery[Races]

    override def copyWithId(valueObject: Race, id: Long) = valueObject.copy(id = id)

    override def findBySkriesimId(skriesimId: Option[Long])(implicit session: Session): Option[Race] = {
      Logger.debug(s"finding Race by skriesimId: $skriesimId")
      tableReference.filter(_.skriesimId === skriesimId).firstOption
    }
  }

  trait RaceRepository extends CRUDRepository[Race] {
    def findBySkriesimId(skriesimId: Option[Long])(implicit session: Session): Option[Race]
  }

}
