package services.statistics.db

import models.statistics.{Person, Club}
import models.statistics.db.Clubs

trait ClubsRepositoryComponent {

  val clubsRepository: ClubsRepository

  import play.api.db.slick.Config.driver.simple._

  class DefaultClubsRepository extends ClubsRepository {
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
      clubs.filter(_.skriesimId === skriesimId).firstOption
    }
  }

  trait ClubsRepository {
    def insert(club: Club)(implicit session: Session): Club

    def findBySkriesimId(skriesimId: Option[Long])(implicit session: Session): Option[Club]
  }
}
