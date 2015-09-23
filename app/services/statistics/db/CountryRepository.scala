package services.statistics.db

import javax.inject.Inject

import com.google.inject.ImplementedBy
import models.statistics.Country
import models.statistics.db.{CountriesTableComponent, CountriesTable}
import play.api.db.slick.DatabaseConfigProvider
import services.statistics.db.support.{CRUDRepository, DefaultCRUDRepository}
import utils.FutureUtils._

@ImplementedBy(classOf[DefaultCountryRepository])
trait CountryRepository extends CRUDRepository[Country] {
  def byCode(code: String): Option[Country]

  def byName(name: String): Option[Country]
}

class DefaultCountryRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends DefaultCRUDRepository[Country, CountriesTable] with CountryRepository with CountriesTableComponent {

  import driver.api._

  override val tableReference = TableQuery[CountriesTable]

  override def copyWithId(valueObject: Country, id: Long) = valueObject.copy(id = id)

  override def byCode(code: String) = {
    val action = tableReference.filter(_.code === code).result.headOption
    db.run(action).await()
  }

  override def byName(name: String) = {
    val action = tableReference.filter(_.name === name).result.headOption
    db.run(action).await()
  }
}
