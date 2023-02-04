package botLogic

import vkAPI.Keyboard

case class Message(text: String, keyboard: Option[Keyboard] = None)
