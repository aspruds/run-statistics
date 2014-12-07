package services.statistics.db.support

import models.statistics.metadata.WithMetadata
import models.statistics.metadata.db.WithMetadatas
import play.api.db.slick.Config.driver.simple._

abstract class DefaultCRUDRepository[M <: WithMetadata, T <: Table[M] with WithMetadatas[M]] extends CRUDRepository[M] {

  type VO = T#TableElementType

  val tableReference: TableQuery[T]

  def copyWithId(valueObject: VO, id: Long): VO

  private lazy val tableReferenceForInsert = {
    val insertInvoker = tableReference returning tableReference.map(_.id)
    insertInvoker into {
      case (valueObject, id) => copyWithId(valueObject, id)
    }
  }

  override def insert(valueObject: VO)(implicit session: Session): VO = {
    tableReferenceForInsert.insert(valueObject)
  }

  override def findById(id: Long)(implicit session: Session): Option[VO] = {
    tableReference.filter(_.id === id).firstOption
  }

  override def deleteById(id: Long)(implicit session: Session): Boolean = {
    tableReference.filter(_.id === id).delete == 1
  }

  override def updateById(id: Long, valueObject: VO)(implicit s: Session): Boolean = {
    tableReference.filter(_.id === id).update(valueObject) == 1
  }
}