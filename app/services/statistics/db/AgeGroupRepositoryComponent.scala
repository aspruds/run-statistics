package services.statistics.db

import models.statistics.AgeGroup
import models.statistics.db.AgeGroups
import play.Logger
import services.statistics.db.support.{CRUDRepository, DefaultCRUDRepository}

trait AgeGroupRepositoryComponent {

  val ageGroupRepository: AgeGroupRepository

  import play.api.db.slick.Config.driver.simple._

  class DefaultAgeGroupRepository extends DefaultCRUDRepository[AgeGroup, AgeGroups] with AgeGroupRepository {
    override val tableReference = TableQuery[AgeGroups]

    override def copyWithId(valueObject: AgeGroup, id: Long) = valueObject.copy(id=id)

    override def findByName(name: String)(implicit session: Session): Option[AgeGroup] = {
      Logger.debug(s"finding AgeGroup by name: $name")
      tableReference.filter(_.name === name).firstOption
    }
  }

  trait AgeGroupRepository extends CRUDRepository[AgeGroup] {
    def insert(ageGroup: AgeGroup)(implicit session: Session): AgeGroup

    def findByName(name: String)(implicit session: Session): Option[AgeGroup]
  }
}
