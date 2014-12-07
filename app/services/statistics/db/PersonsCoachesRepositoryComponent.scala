package services.statistics.db

import models.statistics.db.PersonsCoaches
import models.statistics.{Person, PersonCoach}
import org.joda.time.LocalDateTime
import services.statistics.db.support.{CRUDRepository, DefaultCRUDRepository}

trait PersonsCoachesRepositoryComponent {

  val personsCoachesRepository: PersonsCoachesRepository

  import play.api.db.slick.Config.driver.simple._

  class DefaultPersonsCoachesRepository extends DefaultCRUDRepository[PersonCoach, PersonsCoaches] with PersonsCoachesRepository {
    override val tableReference = TableQuery[PersonsCoaches]

    override def copyWithId(valueObject: PersonCoach, id: Long) = valueObject.copy(id = id)

    override def find(personId: Long, coachId: Long)(implicit session: Session) = {
      tableReference.filter(pc => pc.personId === personId && pc.coachId === coachId).firstOption
    }

    override def insert(person: Person, coach: Person)(implicit session: Session) = {
      val personCoach = PersonCoach(0, person.id, coach.id, Some(new LocalDateTime), None)
      insert(personCoach)
    }
  }

  trait PersonsCoachesRepository extends CRUDRepository[PersonCoach] {
    def find(personId: Long, coachId: Long)(implicit session: Session): Option[PersonCoach]

    def insert(person: Person, coach: Person)(implicit session: Session): PersonCoach
  }

}
