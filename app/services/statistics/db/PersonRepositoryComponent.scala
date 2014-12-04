package services.statistics.db

import models.statistics.{PersonClub, Person}
import models.statistics.db.Persons
import play.Logger
import services.statistics.db.support.CRUDRepository

trait PersonRepositoryComponent {

  val personRepository: PersonRepository

  import play.api.db.slick.Config.driver.simple._

  class DefaultPersonRepository extends PersonRepository {
    val persons = TableQuery[Persons]

    private val personsAutoInc = {
      val insertInvoker = persons returning persons.map(_.id)
      insertInvoker into {
        case (url, id) => url.copy(id = id)
      }
    }

    override def insert(person: Person)(implicit session: Session): Person = {
      personsAutoInc.insert(person)
    }

    override def findBySkriesimId(skriesimId: Option[Long])(implicit session: Session): Option[Person] = {
      Logger.debug(s"finding Person by skriesimId: $skriesimId")
      persons.filter(_.skriesimId === skriesimId).firstOption
    }
  }

  trait PersonRepository extends CRUDRepository[Person] {
    def findBySkriesimId(skriesimId: Option[Long])(implicit session: Session): Option[Person]
  }
}
