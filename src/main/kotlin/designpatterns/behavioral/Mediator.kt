package designpatterns.behavioral

/**
 *  Mediator design patter is sued to provide a centralized communication medium  between different objects in a system.
 */
class ChatUser (val name: String, private val chatMediator: ChatMediator){
    fun send(msg: String) {
        println("Sending a message: $msg")
        chatMediator.sendMessage(msg, this)
    }

    fun receive(msg: String) {
        println("$name received the message: $msg")
    }
}
class ChatMediator{
    private val chatUsers = mutableListOf<ChatUser>()
    fun sendMessage(msg: String, user: ChatUser) {
        chatUsers.filter {
            it != user
        }.forEach {
            it.receive(msg)
        }
    }

    fun addUser(user: ChatUser): ChatMediator = apply {
        chatUsers.add(user)
    }
}

fun main() {
    val mediator = ChatMediator()
    val user = ChatUser(name = "Alpesh", chatMediator = mediator)
    mediator.addUser(ChatUser("ravi", mediator))
        .addUser(user)
        .addUser(ChatUser("Saikat", mediator))
    user.send("Hello")
}