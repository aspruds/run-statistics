package services.statistics.db.support

import models.statistics.metadata.WithMetadata
import play.api.db.slick.Config.driver.simple._

trait CRUDRepository[M <: WithMetadata] {
  def insert(valueObject: M)(implicit session: Session): M
}