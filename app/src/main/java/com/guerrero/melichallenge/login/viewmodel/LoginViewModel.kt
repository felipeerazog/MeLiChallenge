package com.guerrero.melichallenge.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guerrero.melichallenge.base.REDIRECT_URI
import com.guerrero.melichallenge.login.business.AuthUseCase
import com.guerrero.melichallenge.login.view.LoginViewState
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel() {

    private val loadingFlag = MutableLiveData<Boolean>().apply { value = false }
    fun getLoadingFlagObservable(): LiveData<Boolean> = loadingFlag

    private val accessToken = MutableLiveData<String>().apply { value = "" }
    fun getAccessTokenObservable(): LiveData<String> = accessToken

    private val viewState = MutableLiveData<LoginViewState>().apply { value = LoginViewState.Normal() }
    fun getViewStateObservable(): LiveData<LoginViewState> = viewState

    fun login(data: String) {
        if (data.contains(REDIRECT_URI)) {
            viewState.value = LoginViewState.Loading()
            viewModelScope.launch {
                val token = withContext(Dispatchers.IO) {
                    authUseCase(data)
                }
                if (token.isNotEmpty()) {
                    viewState.value = LoginViewState.WebAuthSuccessful(token)
                }
            }
        }
    }
}

