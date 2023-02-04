package vkAPI

import java.net.URLEncoder
import httpHelpers.HttpHelper

class VkRequestUrlBuilder(token: String, id: String) {
  def createConnectionRequestUrl(): String = {
    createRequestUrl("groups.getLongPollServer",
      Map("access_token" -> Some(token), "group_id" -> Some(id), "v" -> Some(Constants.vkVersion)))
  }

  def createMessage(updateResponse: NestedUpdateResponse): String = {
    val message = botLogic.MessageHandler().createAnswer(updateResponse.`object`.message.text.toLowerCase)

    createRequestUrl("messages.send",
      Map(
        "access_token" -> Some(token),
        "user_id" -> Some(updateResponse.`object`.message.from_id.toString),
        "message" -> Some(message.text),
        "group_id" -> Some(id),
        "v" -> Some(Constants.vkVersion),
        "random_id" -> Some(updateResponse.`object`.message.random_id.toString),
        "keyboard" -> {
          message.keyboard match {
            case Some(keyboard: Keyboard) => Some(upickle.default.write(keyboard))
            case _ => None
          }
        }
      ))
  }

  def createRequestUrl(method: String, params: Map[String, Option[String]]): String = {
    HttpHelper.buildUrl(f"https://api.vk.com/method/$method", params)
  }
}

object VkRequestUrlBuilder {
  def createLongPollRequestUrl(serverUrl: String, key: String, ts: String): String =
    HttpHelper.buildUrl(serverUrl, Map(
      "act" -> Some("a_check"),
      "key" -> Some(key),
      "ts" -> Some(ts),
      "wait" -> Some("25")
    ))
}
