package services.statistics.db

import javax.inject.Inject

import com.google.inject.ImplementedBy
import models.statistics.Race
import models.statistics.db.{RacesTableComponent, RacesTable}
import play.Logger
import play.api.db.slick.DatabaseConfigProvider
import services.statistics.db.support.{CRUDRepository, DefaultCRUDRepository}
import utils.FutureUtils._

@ImplementedBy(classOf[DefaultRaceRepository])
trait RaceRepository extends CRUDRepository[Race] {
  def findBySkriesimId(skriesimId: Option[Long]): Option[Race]

  def findBySportlatId(sportlatId: Option[Long]): Option[Race]
}

class DefaultRaceRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends DefaultCRUDRepository[Race, RacesTable] with RaceRepository with RacesTableComponent {

  import driver.api._

  override val tableReference = TableQuery[RacesTable]

  override def copyWithId(valueObject: Race, id: Long) = valueObject.copy(id = id)

  override def findBySkriesimId(skriesimId: Option[Long]): Option[Race] = {
    Logger.debug(s"finding Race by skriesimId: $skriesimId")
    val action = tableReference.filter(_.skriesimId === skriesimId).result.headOption
    db.run(action).await()
  }

  override def findBySportlatId(sportlatId: Option[Long]): Option[Race] = {
    Logger.debug(s"finding Race by sportlatId: $sportlatId")
    val action = tableReference.filter(_.sportlatId === sportlatId).result.headOption
    db.run(action).await()
  }
}
