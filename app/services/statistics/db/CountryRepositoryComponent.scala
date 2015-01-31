package services.statistics.db

import models.statistics.Country
import models.statistics.db.Countries
import services.statistics.db.support.{CRUDRepository, DefaultCRUDRepository}

trait CountryRepositoryComponent {

  val countryRepository: CountryRepository

  import play.api.db.slick.Config.driver.simple._

  class DefaultCountryRepository extends DefaultCRUDRepository[Country, Countries] with CountryRepository {
    override val tableReference = TableQuery[Countries]

    override def copyWithId(valueObject: Country, id: Long) = valueObject.copy(id = id)

    override def byCode(code: String)(implicit session: Session) = {
      tableReference.filter(_.code === code).firstOption
    }

    override def byName(name: String)(implicit session: Session) = {
      tableReference.filter(_.name === name).firstOption
    }
  }

  trait CountryRepository extends CRUDRepository[Country] {
    def byCode(code: String)(implicit session: Session): Option[Country]

    def byName(name: String)(implicit session: Session): Option[Country]
  }

}
