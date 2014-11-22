package utils.text

object TextUtils {

  def toOption(text: String) = {
    if(text.length > 0)
      Some(text)
    else {
      None
    }
  }

  val nbsp = "\u00a0"

}
