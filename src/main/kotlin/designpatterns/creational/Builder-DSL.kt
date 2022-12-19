package designpatterns.creational

import java.io.File

/***
 * Creator pattern
 * In software engineering, creational design patterns are design patterns that deal with object creation mechanisms,
 * trying to create objects in a manner suitable to the situation. The basic form of object creation could result in design problems or added complexity to the design.
 * Creational design patterns main.contest.codeforces.`790`.solve this problem by somehow controlling this object creation.
 */

/***
 *v The builder pattern is used to create complex objects with constituent parts that must be created in the same order
 *  or using a specific algorithm. An external class controls the construction algorithm.
 */

// DSL like


//Builder:
class DialogBuilder() {
    constructor(init: DialogBuilder.() -> Unit) : this() {
        init()
    }

    private var titleHolder: TextView? = null
    private var messageHolder: TextView? = null
    private var imageHolder: File? = null

    fun title(init: TextView.() -> Unit) {
        titleHolder = TextView().apply { init() }
    }

    fun message(init: TextView.() -> Unit) {
        messageHolder = TextView().apply { init() }
    }

    fun image(init: () -> File) {
        imageHolder = init()
    }

    fun build(): Dialog {
        val dialog = Dialog()

        titleHolder?.apply {
            dialog.setTitle(text)
            dialog.setTitleColor(color)
            dialog.showTitle()
        }

        messageHolder?.apply {
            dialog.setMessage(text)
            dialog.setMessageColor(color)
            dialog.showMessage()
        }

        imageHolder?.apply {
            dialog.showImage(readBytes())
        }

        return dialog
    }

    class TextView {
        var text: String = ""
        var color: String = "#00000"
    }
}

fun main() {
    //Function that creates dialog builder and builds Dialog
    fun dialog(init: DialogBuilder.() -> Unit): Dialog {
        return DialogBuilder(init).build()
    }

    val dialog: Dialog = dialog {
        title {
            text = "Dialog Title"
        }
        message {
            text = "Dialog Message"
            color = "#333333"
        }
        image {
            File.createTempFile("image", "jpg")
        }
    }

    dialog.show()
}