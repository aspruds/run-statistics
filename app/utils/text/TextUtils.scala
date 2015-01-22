package utils.text

object TextUtils {

  def toOption(text: String) = {
    if (text.length > 0)
      Some(text)
    else {
      None
    }
  }

  def toLongOption(text: String): Option[Long] = {
    toOption(text).flatMap {
      case(text) =>

        if (text.forall(_.isDigit))
          Some(text.toLong)
        else
          None
    }
  }

  def removeNbsp(string: String) = {
    string.replaceAll(HtmlConstants.NbspUnicode, "").trim
  }
}
