package vkAPI

import java.net.URLEncoder
import httpHelpers.HttpHelper

class VkRequestUrlBuilder(token: String, id: String) {
  def createConnectionRequestUrl(): String = {
    createRequestUrl("groups.getLongPollServer",
      Map("access_token" -> token, "group_id" -> id, "v" -> Constants.vkVersion))
  }

  def createRequest(i: NestedUpdateResponse): String = {
    val answer = URLEncoder.encode(botLogic.MessageHandler().createAnswer(i.`object`.message.text), "UTF-8")

    createRequestUrl("messages.send",
      Map(
        "access_token" -> token,
        "user_id" -> i.`object`.message.from_id.toString,
        "message" -> answer,
        "group_id" -> id,
        "v" -> Constants.vkVersion,
        "random_id" -> i.`object`.message.random_id.toString
      ))
  }

  def createRequestUrl(method: String, params: Map[String, String]): String = {
    HttpHelper.buildUrl(f"https://api.vk.com/method/$method", params)
  }
}

object VkRequestUrlBuilder {
  def createLongPollRequestUrl(serverUrl: String, key: String, ts: String): String =
    HttpHelper.buildUrl(serverUrl, Map(
      "act" -> "a_check",
      "key" -> key,
      "ts" -> ts,
      "wait" -> "25"
    ))
}
