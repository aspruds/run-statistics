package models.skriesim

case class Club(
                 id: Option[Long],
                 name: String,
                 country: Option[String],
                 title: Option[String],
                 description: Option[String],
                 fullDescription: Option[String]
                 )
