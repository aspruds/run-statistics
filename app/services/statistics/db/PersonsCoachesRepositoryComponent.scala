package services.statistics.db

import models.statistics.PersonCoach
import models.statistics.db.PersonsCoaches
import services.statistics.db.support.{CRUDRepository, DefaultCRUDRepository}

trait PersonsCoachesRepositoryComponent {

  val personsCoachesRepository: PersonsCoachesRepository

  import play.api.db.slick.Config.driver.simple._

  class DefaultPersonsCoachesRepository extends DefaultCRUDRepository[PersonCoach, PersonsCoaches] with PersonsCoachesRepository {
    override val tableReference = TableQuery[PersonsCoaches]

    override def copyWithId(valueObject: PersonCoach, id: Long) = valueObject.copy(id=id)
  }

  trait PersonsCoachesRepository extends CRUDRepository[PersonCoach]
}
