package designpatterns.behavioral

/**
 * The command pattern is used to express a request, including the call to be made and all of its required parameters,
 * in a command object. The command may then be executed immediately or held for later use.
 */
interface Command {
    fun execute()
}

class OrderAddCommand(private val id: Long): Command {
    override fun execute() = println("Order took for $id")
}

class OrderPayCommand(private val id: Long): Command {
    override fun execute() {
        println("Order Paid for $id")
    }
}

class CommandProcessor {
    private val queue = ArrayList<Command>()
    fun addToQueue(command: Command) = apply {
        queue.add(command)
    }

    fun processCommands() = apply {
        queue.forEach {
            it.execute()
        }
        queue.clear()
    }
}

fun main() {
    CommandProcessor()
        .addToQueue(OrderAddCommand(3))
        .processCommands()
        .addToQueue(OrderAddCommand(4))
        .addToQueue(OrderPayCommand(4))
        .processCommands()
}