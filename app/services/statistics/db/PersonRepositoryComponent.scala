package services.statistics.db

import models.statistics.Person
import models.statistics.db.Persons
import play.Logger
import services.statistics.db.support.{CRUDRepository, DefaultCRUDRepository}

trait PersonRepositoryComponent {

  val personRepository: PersonRepository

  import play.api.db.slick.Config.driver.simple._

  class DefaultPersonRepository extends DefaultCRUDRepository[Person, Persons] with PersonRepository {
    override val tableReference = TableQuery[Persons]

    override def copyWithId(valueObject: Person, id: Long) = valueObject.copy(id=id)

    override def findBySkriesimId(skriesimId: Long)(implicit session: Session): Option[Person] = {
      Logger.debug(s"finding Person by skriesimId: $skriesimId")
      tableReference.filter(_.skriesimId === skriesimId).firstOption
    }
  }

  trait PersonRepository extends CRUDRepository[Person] {
    def findBySkriesimId(skriesimId: Long)(implicit session: Session): Option[Person]
  }
}
