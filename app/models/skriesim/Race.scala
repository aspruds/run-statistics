package models.skriesim

case class Race(
                 id: Option[Int],
                 name: String,
                 date: String,
                 countryCode: String,
                 url: Option[String]
                 )