package vkAPI

case class Keyboard(one_time: Boolean, inline: Boolean, buttons: List[List[Button]])

case class Button(color: String, action: TextAction)

case class Action(`type`: String)

case class TextAction(label: String, payload: String = "", `type`: String)

object TextAction {
  implicit val rw: upickle.default.ReadWriter[TextAction] = upickle.default.macroRW
}

object Button {
  implicit val rw: upickle.default.ReadWriter[Button] = upickle.default.macroRW
}

object Keyboard {
  implicit val rw: upickle.default.ReadWriter[Keyboard] = upickle.default.macroRW
}