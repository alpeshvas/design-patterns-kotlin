package designpatterns.behavioral

import kotlin.properties.Delegates

interface TextChangeListener{
    fun onTextChanged(old: String, new: String)
}

class PrintingTextChangeListenr: TextChangeListener{
    private var text = ""
    override fun onTextChanged(old: String, new: String) {
        text = "Text is changed from $old to $new"
        println(text)
    }
}

class TextView {
    val listeners = mutableListOf<TextChangeListener>()

    var text: String by Delegates.observable("init") { _, old, new ->
        listeners.forEach { it.onTextChanged(old, new) }
    }
}

fun main() {
    val textView = TextView()
    textView.listeners.add(PrintingTextChangeListenr())
    textView.text = "Hello"
    textView.text = "World"
}