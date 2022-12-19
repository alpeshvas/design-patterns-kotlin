package designpatterns.behavioral

// The strategy pattern is used to create an interchangeable family of algorithms from which the required process is chosen at run-time.

class Printer(private val stringFormatterStrategy: (String) -> String) {
    fun printString(string: String) = println(stringFormatterStrategy(string))
}

val lowerCaseFormatter: (String) -> String = { it.lowercase() }
val upperCaseFormatter: (String) -> String = { it.uppercase() }

fun main() {
    val inputString = "alpesh is awesome"
    var printer = Printer(lowerCaseFormatter)
    printer.printString(inputString)
    printer = Printer(upperCaseFormatter)
    printer.printString("alpesh is awesome")
}
