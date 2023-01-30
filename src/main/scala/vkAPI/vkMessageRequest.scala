package vkAPI

import java.net.URLEncoder

class vkMessageRequest {
  def createRequest(i:  VkServerRequest#NestedUpdateResponse): String = {
    val answer = URLEncoder.encode(botLogic.MessageHandler().createAnswer(i.`object`.message.text), "UTF-8")

    vkAPI.VkHTTPHandler().createRequest("messages.send",
      Map(
        "access_token" -> vkAPI.VkServerRequest().token,
        "user_id" -> i.`object`.message.from_id.toString,
        "message" -> answer,
        "group_id" -> vkAPI.VkServerRequest().id,
        "v" -> "5.131",
        "random_id" -> i.`object`.message.random_id.toString
      ))
  }
}
