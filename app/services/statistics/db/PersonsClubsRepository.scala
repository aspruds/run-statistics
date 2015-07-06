package services.statistics.db

import javax.inject.Inject

import com.google.inject.ImplementedBy
import models.statistics.db.PersonsClubsTable
import models.statistics.{Club, Person, PersonClub}
import org.joda.time.LocalDateTime
import play.api.db.slick.DatabaseConfigProvider
import services.statistics.db.support.{CRUDRepository, DefaultCRUDRepository}
import utils.FutureUtils._

@ImplementedBy[DefaultPersonsClubsRepository]
trait PersonsClubsRepository extends CRUDRepository[PersonClub] {
  def find(personId: Long, clubId: Long): Option[PersonClub]

  def insert(person: Person, club: Club): PersonClub
}

class DefaultPersonsClubsRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends DefaultCRUDRepository[PersonClub, PersonsClubsTable] with PersonsClubsRepository {

  import driver.api._

  override val tableReference = TableQuery[PersonsClubsTable]

  override def copyWithId(valueObject: PersonClub, id: Long) = valueObject.copy(id = id)

  override def find(personId: Long, clubId: Long) = {
    val action = tableReference.filter(pc =>
      pc.personId === personId && pc.clubId === clubId).result.headOption
    db.run(action).await()
  }

  override def insert(person: Person, club: Club) = {
    val personClub = PersonClub(0, person.id, club.id, Some(new LocalDateTime), None)
    insert(personClub)
  }
}