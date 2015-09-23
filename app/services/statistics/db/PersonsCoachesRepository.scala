package services.statistics.db

import javax.inject.Inject

import com.google.inject.ImplementedBy
import models.statistics.db.{PersonsCoachesTableComponent, PersonsCoachesTable}
import models.statistics.{Person, PersonCoach}
import org.joda.time.LocalDateTime
import play.api.db.slick.DatabaseConfigProvider
import services.statistics.db.support.{CRUDRepository, DefaultCRUDRepository}
import utils.FutureUtils._

@ImplementedBy(classOf[DefaultPersonsCoachesRepository])
trait PersonsCoachesRepository extends CRUDRepository[PersonCoach] {
  def find(personId: Long, coachId: Long): Option[PersonCoach]

  def insert(person: Person, coach: Person): PersonCoach
}

class DefaultPersonsCoachesRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends DefaultCRUDRepository[PersonCoach, PersonsCoachesTable] with PersonsCoachesRepository with PersonsCoachesTableComponent {

  import driver.api._

  override val tableReference = TableQuery[PersonsCoachesTable]

  override def copyWithId(valueObject: PersonCoach, id: Long) = valueObject.copy(id = id)

  override def find(personId: Long, coachId: Long) = {
    val action = tableReference.filter(pc =>
      pc.personId === personId && pc.coachId === coachId).result.headOption
    db.run(action).await()
  }

  override def insert(person: Person, coach: Person) = {
    val personCoach = PersonCoach(0, person.id, coach.id, Some(new LocalDateTime), None)
    insert(personCoach)
  }
}