package models.http

import org.joda.time.LocalDateTime

case class Url(
                id: Long,
                url: String,
                content: String,
                dateVisited: LocalDateTime)
