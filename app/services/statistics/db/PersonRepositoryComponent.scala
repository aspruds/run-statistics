package services.statistics.db

import models.statistics.Person
import models.statistics.db.Persons

trait PersonRepositoryComponent {

  val personRepository: PersonRepository

  import play.api.db.slick.Config.driver.simple._

  class DefaultPersonRepository(implicit session: Session) extends PersonRepository {
    val persons = TableQuery[Persons]

    private val personsAutoInc = {
      val insertInvoker = persons returning persons.map(_.id)
      insertInvoker into {
        case (url, id) => url.copy(id = id)
      }
    }

    override def insert(person: Person): Person = {
      personsAutoInc.insert(person)
    }
  }

  trait PersonRepository {
    def insert(person: Person): Person
  }
}
