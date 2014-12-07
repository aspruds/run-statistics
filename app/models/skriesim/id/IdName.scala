package models.skriesim.id

import models.statistics.metadata.WithName

case class IdName(
                   id: Long,
                   name: String) extends WithName
