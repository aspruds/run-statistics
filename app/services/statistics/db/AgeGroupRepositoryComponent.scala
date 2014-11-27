package services.statistics.db

import models.statistics.AgeGroup
import models.statistics.db.AgeGroups
import play.Logger

trait AgeGroupRepositoryComponent {

  val ageGroupRepository: AgeGroupRepository

  import play.api.db.slick.Config.driver.simple._

  class DefaultAgeGroupRepository extends AgeGroupRepository {
    val ageGroups = TableQuery[AgeGroups]

    private val ageGroupsAutoInc = {
      val insertInvoker = ageGroups returning ageGroups.map(_.id)
      insertInvoker into {
        case (url, id) => url.copy(id = id)
      }
    }

    override def insert(ageGroup: AgeGroup)(implicit session: Session): AgeGroup = {
      ageGroupsAutoInc.insert(ageGroup)
    }

    override def findByName(name: String)(implicit session: Session): Option[AgeGroup] = {
      Logger.debug(s"finding AgeGroup by name: $name")
      ageGroups.filter(_.name === name).firstOption
    }
  }

  trait AgeGroupRepository {
    def insert(ageGroup: AgeGroup)(implicit session: Session): AgeGroup

    def findByName(name: String)(implicit session: Session): Option[AgeGroup]
  }
}
