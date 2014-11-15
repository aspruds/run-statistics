package models

case class RaceId(
                   id: Int,
                   name: String
                   )

case class Race(
                 id: Option[Int],
                 name: String,
                 date: String,
                 countryCode: String,
                 url: Option[String]
                 )