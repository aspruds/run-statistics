package services.statistics.db

import models.statistics.PersonCoach
import models.statistics.db.PersonsCoaches

trait PersonsCoachesRepositoryComponent {

  val personsCoachesRepository: PersonsCoachesRepository

  import play.api.db.slick.Config.driver.simple._

  class DefaultPersonsCoachesRepository extends PersonsCoachesRepository {
    val personsCoaches = TableQuery[PersonsCoaches]

    private val personsCoachesAutoInc = {
      val insertInvoker = personsCoaches returning personsCoaches.map(_.id)
      insertInvoker into {
        case (url, id) => url.copy(id = id)
      }
    }

    override def insert(personCoach: PersonCoach)(implicit session: Session): PersonCoach = {
      personsCoachesAutoInc.insert(personCoach)
    }
  }

  trait PersonsCoachesRepository {
    def insert(personCoach: PersonCoach)(implicit session: Session): PersonCoach
  }
}
