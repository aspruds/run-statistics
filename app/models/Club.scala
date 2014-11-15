package models

case class ClubId(
                   id: Int,
                   name: String)

case class Club(
                 id: Option[Int],
                 name: String,
                 country: String,
                 title: Option[String],
                 description: Option[String],
                 fullDescription: Option[String]
                 )