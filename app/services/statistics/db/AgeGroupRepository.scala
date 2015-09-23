package services.statistics.db

import javax.inject.Inject

import com.google.inject.ImplementedBy
import models.statistics.AgeGroup
import models.statistics.db.{AgeGroupsTable, AgeGroupsTableComponent}
import play.Logger
import play.api.db.slick.DatabaseConfigProvider
import services.statistics.db.support.{CRUDRepository, DefaultCRUDRepository}
import play.api.cache.Cache
import utils.FutureUtils._

@ImplementedBy(classOf[DefaultAgeGroupRepository])
trait AgeGroupRepository extends CRUDRepository[AgeGroup] {
  def findByName(name: String): Option[AgeGroup]
}

class DefaultAgeGroupRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends DefaultCRUDRepository[AgeGroup, AgeGroupsTable] with AgeGroupRepository
  with AgeGroupsTableComponent {

  import driver.api._
  import play.api.Play.current

  override val tableReference = TableQuery[AgeGroupsTable]

  override def copyWithId(valueObject: AgeGroup, id: Long) = valueObject.copy(id = id)

  override def findByName(name: String): Option[AgeGroup] = {
    Cache.getOrElse(s"AgeGroup.name.$name") {
      Logger.debug(s"finding AgeGroup by name: $name")
      val action = tableReference.filter(_.name === name).result.headOption
      db.run(action).await()
    }
  }
}