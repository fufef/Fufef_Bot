package vkAPI

class VkServerRequest(token: String, id: String) {


  def createConnectionRequestUrl(): String = {
    VkHTTPHandler().createRequestUrl("groups.getLongPollServer",
      Map("access_token" -> token, "group_id" -> id, "v" -> Constants.vkVersion))
  }

  def deserializeConnectionResponse(response: String): NestedConnectionResponse =
    upickle.default.read[ConnectionResponse](response).response

  def deserializeUpdateResponse(response: String): UpdateResponse =
    upickle.default.read[UpdateResponse](response)

}
