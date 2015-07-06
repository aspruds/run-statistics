package utils

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

object FutureUtils {

  implicit class FutureUtils[T](future: Future[T]) {
    def await() = Await.result(future, Duration.Inf)
  }

}
