package utils.db

import slick.driver.PostgresDriver

object PortableJodaSupport extends com.github.tototoshi.slick.GenericJodaSupport(PostgresDriver)