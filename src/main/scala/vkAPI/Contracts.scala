package vkAPI


case class NestedConnectionResponse(server: String, key: String, ts: String)

case class ConnectionResponse(response: NestedConnectionResponse)

case class MessageResponse(date: Int, from_id: Int, id: Int, text: String, conversation_message_id: Int,
                           important: Boolean, random_id: Int)

case class ObjectResponse(message: MessageResponse)

case class NestedUpdateResponse(`type`: String, event_id: String, `object`: ObjectResponse)

case class UpdateResponse(ts: String, updates: List[NestedUpdateResponse])

object NestedConnectionResponse {
  implicit val rw: upickle.default.ReadWriter[NestedConnectionResponse] = upickle.default.macroRW
}

object ConnectionResponse {
  implicit val rw: upickle.default.ReadWriter[ConnectionResponse] = upickle.default.macroRW
}

object MessageResponse {
  implicit val rw: upickle.default.ReadWriter[MessageResponse] = upickle.default.macroRW
}

object ObjectResponse {
  implicit val rw: upickle.default.ReadWriter[ObjectResponse] = upickle.default.macroRW
}

object NestedUpdateResponse {
  implicit val rw: upickle.default.ReadWriter[NestedUpdateResponse] = upickle.default.macroRW
}

object UpdateResponse {
  implicit val rw: upickle.default.ReadWriter[UpdateResponse] = upickle.default.macroRW
}