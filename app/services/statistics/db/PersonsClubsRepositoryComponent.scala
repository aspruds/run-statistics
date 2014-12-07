package services.statistics.db

import models.statistics.db.PersonsClubs
import models.statistics.{Club, Person, PersonClub}
import org.joda.time.LocalDateTime
import services.statistics.db.support.{CRUDRepository, DefaultCRUDRepository}

trait PersonsClubsRepositoryComponent {

  val personsClubsRepository: PersonsClubsRepository

  import play.api.db.slick.Config.driver.simple._

  class DefaultPersonsClubsRepository extends DefaultCRUDRepository[PersonClub, PersonsClubs] with PersonsClubsRepository {
    override val tableReference = TableQuery[PersonsClubs]

    override def copyWithId(valueObject: PersonClub, id: Long) = valueObject.copy(id = id)

    override def find(personId: Long, clubId: Long)(implicit session: Session) = {
      tableReference.filter(pc => pc.personId === personId && pc.clubId === clubId).firstOption
    }

    override def insert(person: Person, club: Club)(implicit session: Session) = {
      val personClub = PersonClub(0, person.id, club.id, Some(new LocalDateTime), None)
      insert(personClub)
    }
  }

  trait PersonsClubsRepository extends CRUDRepository[PersonClub] {
    def find(personId: Long, clubId: Long)(implicit session: Session): Option[PersonClub]

    def insert(person: Person, club: Club)(implicit session: Session): PersonClub
  }

}
