package models

case class RaceId(
                   id: Int,
                   name: String
                   )

case class Race(
id: Int,
name: String,
date: String,
country: String,
url: Option[String]
                 )