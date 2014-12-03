package services.statistics.db

import models.statistics.{Person, Club}
import models.statistics.db.Clubs
import play.Logger

trait ClubRepositoryComponent {

  val clubRepository: ClubRepository

  import play.api.db.slick.Config.driver.simple._

  class DefaultClubRepository extends ClubRepository {
    val clubs = TableQuery[Clubs]

    private val clubsAutoInc = {
      val insertInvoker = clubs returning clubs.map(_.id)
      insertInvoker into {
        case (url, id) => url.copy(id = id)
      }
    }

    override def insert(club: Club)(implicit session: Session): Club = {
      clubsAutoInc.insert(club)
    }

    override def findBySkriesimId(skriesimId: Option[Long])(implicit session: Session): Option[Club] = {
      Logger.debug(s"finding Club by skriesimId: $skriesimId")
      clubs.filter(_.skriesimId === skriesimId).firstOption
    }
  }

  trait ClubRepository {
    def insert(club: Club)(implicit session: Session): Club

    def findBySkriesimId(skriesimId: Option[Long])(implicit session: Session): Option[Club]
  }
}
