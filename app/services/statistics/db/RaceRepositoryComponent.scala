package services.statistics.db

import models.statistics.{Race, Person}
import models.statistics.db.{Races, Persons}
import play.Logger

trait RaceRepositoryComponent {

  val raceRepository: RaceRepository

  import play.api.db.slick.Config.driver.simple._

  class DefaultRaceRepository extends RaceRepository {
    val races = TableQuery[Races]

    private val racesAutoInc = {
      val insertInvoker = races returning races.map(_.id)
      insertInvoker into {
        case (url, id) => url.copy(id = id)
      }
    }

    override def insert(race: Race)(implicit session: Session): Race = {
      racesAutoInc.insert(race)
    }

    override def findBySkriesimId(skriesimId: Option[Long])(implicit session: Session): Option[Race] = {
      Logger.debug(s"finding Race by skriesimId: $skriesimId")
      races.filter(_.skriesimId === skriesimId).firstOption
    }
  }

  trait RaceRepository {
    def insert(race: Race)(implicit session: Session): Race

    def findBySkriesimId(skriesimId: Option[Long])(implicit session: Session): Option[Race]
  }
}
