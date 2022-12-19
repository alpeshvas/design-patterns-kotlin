package designpatterns.behavioral

/**
 *  The memento pattern is a software design pattern that provides the ability to restore an object to its previous state (undo via rollback).
 */

data class Memento(val state: String)

class Originator(var state: String) {
    fun createMemento(): Memento = Memento(state)

    fun restoreState(memento: Memento) {
        state = memento.state
    }
}

class CareTaker {
    private val mementoList = mutableListOf<Memento>()

    private var currentIndex = -1

    fun saveState(state: Memento) {
        mementoList.add(state)
        currentIndex ++
    }

    fun undo(): Memento {
        currentIndex--
        return mementoList[currentIndex]
    }
    fun redo(): Memento{
        if(currentIndex<mementoList.size-1) currentIndex++
        return mementoList[currentIndex]
    }
}

fun main() {
    val originator = Originator("init")
    val careTaker = CareTaker()
    careTaker.saveState(originator.createMemento())
    originator.state = "alpesh"
    careTaker.saveState(originator.createMemento())
    originator.state = "ravi"
    careTaker.saveState(originator.createMemento())
    originator.restoreState(careTaker.undo())
    println("undo: ${originator.state}")
    originator.restoreState(careTaker.redo())
    println("redo: ${originator.state}")
    originator.restoreState(careTaker.undo())
    println("undo: ${originator.state}")
}