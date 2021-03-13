package com.guerrero.melichallenge.login.view

open class LoginViewState {
    class Loading : LoginViewState()
    class Normal : LoginViewState()
    class WebAuthSuccessful(val token: String) : LoginViewState()
}
