package vkAPI

import botLogic.MessageWithMenu

import java.net.URLEncoder
import httpHelpers.HttpHelper

class VkRequestUrlBuilder(token: String, id: String) {
  def createConnectionRequestUrl(): String = {
    createRequestUrl("groups.getLongPollServer",
      Map("access_token" -> token, "group_id" -> id, "v" -> Constants.vkVersion))
  }

  def createMessage(updateResponse: NestedUpdateResponse): String = {
    val message = botLogic.MessageHandler().createAnswer(updateResponse.`object`.message.text)
    val answer = URLEncoder.encode(message.text, "UTF-8")
    val keyboard = message match {
      case MessageWithMenu(_, keyboard) => keyboard
      case _ => None
    }

    createRequestUrl("messages.send",
      Map(
        "access_token" -> token,
        "user_id" -> updateResponse.`object`.message.from_id.toString,
        "message" -> answer,
        "group_id" -> id,
        "v" -> Constants.vkVersion,
        "random_id" -> updateResponse.`object`.message.random_id.toString,
        "keyboard" -> keyboard
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
