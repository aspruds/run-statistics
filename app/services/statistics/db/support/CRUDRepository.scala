package services.statistics.db.support

import models.statistics.metadata.WithMetadata

trait CRUDRepository[M <: WithMetadata] {
  def insert(valueObject: M): M

  def findById(id: Long): Option[M]

  def updateById(id: Long, valueObject: M): Boolean

  def deleteById(id: Long): Boolean
}