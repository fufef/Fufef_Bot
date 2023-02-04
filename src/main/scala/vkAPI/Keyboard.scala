package vkAPI

case class Keyboard(one_time: Boolean, inline: Boolean, buttons: List[List[Button]])

case class Button(color: String, action: TextAction)

class Action(`type`: String)

//case class TextAction(label: String, payload: String) extends Action("text")
case class TextAction(label: String, payload: String = "", `type`: String)

//case class VkPayAction(payload: String, hash: String) extends Action("vkpay")

//case class OpenLinkAction(link: String, label: String, payload: String = "") extends Action("open_link")

//object OpenLinkAction {
//  implicit val rw: upickle.default.ReadWriter[OpenLinkAction] = upickle.default.macroRW
//}
//
//object VkPayAction {
//  implicit val rw: upickle.default.ReadWriter[VkPayAction] = upickle.default.macroRW
//}

object TextAction {
  implicit val rw: upickle.default.ReadWriter[TextAction] = upickle.default.macroRW
}

object Button {
  implicit val rw: upickle.default.ReadWriter[Button] = upickle.default.macroRW
}

//object Action {
//  implicit val rw: upickle.default.ReadWriter[Action] = upickle.default.macroRW
//}

object Keyboard {
  implicit val rw: upickle.default.ReadWriter[Keyboard] = upickle.default.macroRW
}