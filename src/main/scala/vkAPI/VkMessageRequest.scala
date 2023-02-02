package vkAPI

import java.net.URLEncoder

class VkMessageRequest(token: String , id: String) {
  def createRequest(i: NestedUpdateResponse): String = {
    val answer = URLEncoder.encode(botLogic.MessageHandler().createAnswer(i.`object`.message.text), "UTF-8")

    vkAPI.VkHTTPHandler().createRequestUrl("messages.send",
      Map(
        "access_token" -> token,
        "user_id" -> i.`object`.message.from_id.toString,
        "message" -> answer,
        "group_id" -> id,
        "v" -> Constants.vkVersion,
        "random_id" -> i.`object`.message.random_id.toString
      ))
  }
}
