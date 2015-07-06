package services.statistics.db.support

import models.statistics.metadata.WithMetadata
import models.statistics.metadata.db.WithMetadatas
import play.api.db.slick.HasDatabaseConfigProvider
import slick.driver.JdbcProfile
import slick.lifted.AbstractTable
import utils.FutureUtils._

abstract class DefaultCRUDRepository[M <: WithMetadata, T <: AbstractTable[M] with WithMetadatas[M]] extends CRUDRepository[M]
with HasDatabaseConfigProvider[JdbcProfile] {
  import driver.api._

  type VO = T#TableElementType

  val tableReference: TableQuery[T]

  def copyWithId(valueObject: VO, id: Long): VO

  private lazy val tableReferenceForInsert = {
    val insertInvoker = tableReference returning tableReference.map(_.id)
    insertInvoker into {
      case (valueObject, id) => copyWithId(valueObject, id)
    }
  }

  override def insert(valueObject: VO): VO = {
    val action = tableReferenceForInsert +=(valueObject)
    db.run(action).await()
  }

  override def findById(id: Long): Option[VO] = {
    val action = tableReference.filter(_.id === id).result.headOption
    db.run(action).await()
  }

  override def deleteById(id: Long): Boolean = {
    val action = tableReference.filter(_.id === id).delete
    db.run(action).await() == 1
  }

  override def updateById(id: Long, valueObject: VO): Boolean = {
    val action = tableReference.filter(_.id === id).update(valueObject)
    db.run(action).await() == 1
  }
}