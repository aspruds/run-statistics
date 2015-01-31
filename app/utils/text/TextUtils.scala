package utils.text

object TextUtils {

  implicit class StringOps(text: String) {
    def toOption: Option[String] = {
      if (text.length > 0)
        Some(text)
      else
        None
    }

    def toLongOption: Option[Long] = text.toOption.flatMap {
      case(text) if (text.forall(_.isDigit)) => Some(text.toLong)
      case _ => None
    }

    def removeNbsp = text.replaceAll(HtmlConstants.NbspUnicode, "").trim
  }
}
