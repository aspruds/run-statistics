package models.http

import org.joda.time.LocalDateTime

case class UrlCache(
                id: Long,
                url: String,
                content: String,
                dateVisited: LocalDateTime)
