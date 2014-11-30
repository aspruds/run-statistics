package services.statistics.db

import models.statistics.{DisciplineType, DisciplineTypes}

trait DisciplineTypeRepositoryComponent {

  val disciplineTypeRepository: DisciplineTypeRepository

  import play.api.db.slick.Config.driver.simple._

  class DefaultDisciplineRepository extends DisciplineTypeRepository {
    val disciplineTypes = TableQuery[DisciplineTypes]

    private val racesAutoInc = {
      val insertInvoker = disciplineTypes returning disciplineTypes.map(_.id)
      insertInvoker into {
        case (url, id) => url.copy(id = id)
      }
    }

    override def insert(disciplineType: DisciplineType)(implicit session: Session): DisciplineType = {
      racesAutoInc.insert(disciplineType)
    }
  }

  trait DisciplineTypeRepository {
    def insert(disciplineType: DisciplineType)(implicit session: Session): DisciplineType
  }
}
