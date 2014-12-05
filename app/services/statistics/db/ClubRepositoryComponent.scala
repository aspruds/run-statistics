package services.statistics.db

import models.statistics.Club
import models.statistics.db.Clubs
import play.Logger
import services.statistics.db.support.{CRUDRepository, DefaultCRUDRepository}

trait ClubRepositoryComponent {

  val clubRepository: ClubRepository

  import play.api.db.slick.Config.driver.simple._

  class DefaultClubRepository extends DefaultCRUDRepository[Club, Clubs] with ClubRepository {
    override val tableReference = TableQuery[Clubs]

    override def copyWithId(valueObject: Club, id: Long) = valueObject.copy(id=id)

    override def findBySkriesimId(skriesimId: Option[Long])(implicit session: Session): Option[Club] = {
      Logger.debug(s"finding Club by skriesimId: $skriesimId")
      tableReference.filter(_.skriesimId === skriesimId).firstOption
    }
  }

  trait ClubRepository extends CRUDRepository[Club] {
    def findBySkriesimId(skriesimId: Option[Long])(implicit session: Session): Option[Club]
  }
}
