package vkAPI

class vkHTTPHandler {

  def createRequest(method: String, params: Map[String, String]): String = {
    val stringParams = params.toArray.map { case (key, value) => key + "=" + value }.mkString(sep = "&")
    "POST https://api.vk.com/method/" + method + "?" + stringParams + " HTTP/1.1"
  }

  def sendRequest(): Unit = {}
}
