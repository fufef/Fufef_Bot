package vkAPI

class VkHTTPHandler {

  def createRequestUrl(method: String, params: Map[String, String]): String = {
    val stringParams = params.map { case (key, value) => key + "=" + value }.mkString(sep = "&")
    f"https://api.vk.com/method/$method?$stringParams"
  }

  def createServerRequestUrl(serverUrl: String, key: String, ts: String): String =
    f"$serverUrl?act=a_check&key=$key&ts=$ts&wait=25"
}
