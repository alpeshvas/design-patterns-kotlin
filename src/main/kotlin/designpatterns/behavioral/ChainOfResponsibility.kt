package designpatterns.behavioral

enum class LogLevel(private val value: Int){
    DEBUG(0),
    INFO(1),
    WARN(2),
    ERROR(3)
}
abstract class Logger(private val level: LogLevel){
    var nextLogger: Logger? = null
    abstract fun write(message: String)

    fun log(messageLevel: LogLevel, message: String){
        if(messageLevel >= this.level){
            write(message)
        }
        nextLogger?.log(messageLevel, message)
    }
}

class DebugLogger: Logger(LogLevel.DEBUG){
    override fun write(message: String) {
        println("DEBUG: $message")
    }
}

class InfoLogger: Logger(LogLevel.INFO){
    override fun write(message: String) {
        println("INFO: $message")
    }
}

fun main() {
    val debugLogger: Logger = DebugLogger()
    debugLogger.nextLogger = InfoLogger()
//    debugLogger.nextLogger!!.nextLogger = DebugLogger()
    debugLogger.log(LogLevel.DEBUG, "hey there buddy")
    debugLogger.log(LogLevel.INFO, "Hey what's up")
}