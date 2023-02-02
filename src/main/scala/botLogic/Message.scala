package botLogic

import vkAPI.Keyboard

class Message(text: String)

case class MessageWithMenu(text: String, keyboard: Keyboard) extends Message(text) {

}