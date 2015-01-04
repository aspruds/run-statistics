package services.statistics.db

import models.statistics.AgeGroup
import models.statistics.db.AgeGroups
import play.Logger
import play.api.cache.Cache
import services.statistics.db.support.{CRUDRepository, DefaultCRUDRepository}
import play.api.Play.current

trait AgeGroupRepositoryComponent {

  val ageGroupRepository: AgeGroupRepository

  import play.api.db.slick.Config.driver.simple._

  class DefaultAgeGroupRepository extends DefaultCRUDRepository[AgeGroup, AgeGroups] with AgeGroupRepository {
    override val tableReference = TableQuery[AgeGroups]

    override def copyWithId(valueObject: AgeGroup, id: Long) = valueObject.copy(id = id)

    override def findByName(name: String)(implicit session: Session): Option[AgeGroup] = {
      Cache.getOrElse(s"AgeGroup.name.$name") {
        Logger.debug(s"finding AgeGroup by name: $name")
        tableReference.filter(_.name === name).firstOption
      }
    }
  }

  trait AgeGroupRepository extends CRUDRepository[AgeGroup] {
    def findByName(name: String)(implicit session: Session): Option[AgeGroup]
  }

}
