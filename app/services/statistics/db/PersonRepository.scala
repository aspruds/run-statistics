package services.statistics.db

import javax.inject.Inject

import com.google.inject.ImplementedBy
import models.statistics.Person
import models.statistics.db._
import play.Logger
import play.api.db.slick.DatabaseConfigProvider
import services.statistics.db.support.{CRUDRepository, DefaultCRUDRepository}
import utils.FutureUtils._

@ImplementedBy[DefaultPersonRepository]
trait PersonRepository extends CRUDRepository[Person] {
  def findBySkriesimId(skriesimId: Long): Option[Person]

  def findBySportlatId(sportlatId: Long): Option[Person]
}

class DefaultPersonRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
extends DefaultCRUDRepository[Person, PersonsTable] with PersonRepository {

  import driver.api._

  override val tableReference = TableQuery[PersonsTable]

  override def copyWithId(valueObject: Person, id: Long) = valueObject.copy(id = id)

  override def findBySkriesimId(skriesimId: Long): Option[Person] = {
    Logger.debug(s"finding Person by skriesimId: $skriesimId")
    val action = tableReference.filter(_.skriesimId === skriesimId).result.headOption
    db.run(action).await()
  }

  override def findBySportlatId(sportlatId: Long): Option[Person] = {
    Logger.debug(s"finding Person by sportlatId: $sportlatId")
    val action = tableReference.filter(_.sportlatId === sportlatId).result.headOption
    db.run(action).await()
  }
}