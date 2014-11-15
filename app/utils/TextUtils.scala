package utils

object TextUtils {

  def toOption(text: String) = {
    if(text.length > 0)
      Some(text)
    else {
      None
    }
  }

}
