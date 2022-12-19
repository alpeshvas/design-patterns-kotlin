package designpatterns.behavioral

/*
The state pattern is used to alter the behaviour of an object as its internal state changes.
The pattern allows the class for an object to apparently change at run-time.
 */

sealed class AuthorizationState

class Authorized(val userId: String): AuthorizationState()
object UnAuthorized: AuthorizationState()

class AuthorizationPresenter {
    private var state: AuthorizationState = UnAuthorized
    val isAuthorized: Boolean
        get() = when(state) {
        is Authorized -> true
        is UnAuthorized -> false
    }

    val userName: String
        get() = when(state) {
            is Authorized -> (state as Authorized).userId
            is UnAuthorized -> "Unknown"
        }

    fun loginUser(id: String) {
        state = Authorized(id)
    }

    fun logoutUser(){
        state = UnAuthorized
    }

    override fun toString(): String {
        return "user :$userName is logged in: $isAuthorized"
    }
}

fun main() {
    val authorizationPresenter = AuthorizationPresenter()
    println(authorizationPresenter)
    authorizationPresenter.logoutUser()
    authorizationPresenter.loginUser("alpesh")
    println(authorizationPresenter)
    authorizationPresenter.logoutUser()
}