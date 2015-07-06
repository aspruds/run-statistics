package services.statistics.db

import javax.inject.Inject

import com.google.inject.ImplementedBy
import models.statistics.Club
import models.statistics.db.ClubsTable
import play.Logger
import play.api.db.slick.DatabaseConfigProvider
import services.statistics.db.support.{CRUDRepository, DefaultCRUDRepository}
import utils.FutureUtils._

@ImplementedBy[DefaultClubRepository]
trait ClubRepository extends CRUDRepository[Club] {
  def findBySkriesimId(skriesimId: Option[Long]): Option[Club]
}

class DefaultClubRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends DefaultCRUDRepository[Club, ClubsTable] with ClubRepository {

  import driver.api._

  override val tableReference = TableQuery[ClubsTable]

  override def copyWithId(valueObject: Club, id: Long) = valueObject.copy(id = id)

  override def findBySkriesimId(skriesimId: Option[Long]): Option[Club] = {
    Logger.debug(s"finding Club by skriesimId: $skriesimId")
    val action = tableReference.filter(_.skriesimId === skriesimId).result.headOption
    db.run(action).await()
  }
}
