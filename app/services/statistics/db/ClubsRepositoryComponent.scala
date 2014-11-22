package services.statistics.db

import models.statistics.Club
import models.statistics.db.Clubs

trait ClubsRepositoryComponent {

  val clubsRepository: ClubsRepository

  import play.api.db.slick.Config.driver.simple._

  class DefaultClubsRepository(implicit session: Session) extends ClubsRepository {
    val clubs = TableQuery[Clubs]

    private val clubsAutoInc = {
      val insertInvoker = clubs returning clubs.map(_.id)
      insertInvoker into {
        case (url, id) => url.copy(id = id)
      }
    }

    override def insert(club: Club): Club = {
      clubsAutoInc.insert(club)
    }
  }

  trait ClubsRepository {
    def insert(club: Club): Club
  }
}
