package vkAPI

class VkHTTPHandler {

  def createRequest(method: String, params: Map[String, String]): String = {
    val stringParams = params.toArray.map { case (key, value) => key + "=" + value }.mkString(sep = "&")
    f"https://api.vk.com/method/$method?$stringParams"
  }

  def createServerRequest(server: String, key: String, ts: String): String =
    f"$server?act=a_check&key=$key&ts=$ts&wait=25"
}
