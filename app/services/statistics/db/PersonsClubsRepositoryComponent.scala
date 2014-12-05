package services.statistics.db

import models.statistics.PersonClub
import models.statistics.db.PersonsClubs
import services.statistics.db.support.{CRUDRepository, DefaultCRUDRepository}

trait PersonsClubsRepositoryComponent {

  val personsClubsRepository: PersonsClubsRepository

  import play.api.db.slick.Config.driver.simple._

  class DefaultPersonsClubsRepository extends DefaultCRUDRepository[PersonClub, PersonsClubs] with PersonsClubsRepository {
    override val tableReference = TableQuery[PersonsClubs]

    override def copyWithId(valueObject: PersonClub, id: Long) = valueObject.copy(id=id)
  }

  trait PersonsClubsRepository extends CRUDRepository[PersonClub]
}
