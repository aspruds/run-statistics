package services.statistics.db

import models.statistics.PersonClub
import models.statistics.db.PersonsClubs

trait PersonsClubsRepositoryComponent {

  val personsClubsRepository: PersonsClubsRepository

  import play.api.db.slick.Config.driver.simple._

  class DefaultPersonsClubsRepository extends PersonsClubsRepository {
    val personsClubs = TableQuery[PersonsClubs]

    private val clubsPersonsAutoInc = {
      val insertInvoker = personsClubs returning personsClubs.map(_.id)
      insertInvoker into {
        case (url, id) => url.copy(id = id)
      }
    }

    override def insert(personClub: PersonClub)(implicit session: Session): PersonClub = {
      clubsPersonsAutoInc.insert(personClub)
    }
  }

  trait PersonsClubsRepository {
    def insert(personClub: PersonClub)(implicit session: Session): PersonClub
  }
}
