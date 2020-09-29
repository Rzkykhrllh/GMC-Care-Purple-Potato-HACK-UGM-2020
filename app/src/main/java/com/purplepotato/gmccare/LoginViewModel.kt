package com.purplepotato.gmccare

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LoginViewModel(private val fbAuth: FirebaseAuthentication) : ViewModel() {

    private val _signIn = MutableLiveData<State<Boolean>>()

    fun emailAndPasswordSignIn(email: String, password: String) = viewModelScope.launch {
        _signIn.postValue(State.OnLoading())

        fbAuth.emailAndPasswordSignIn(email, password, {
            _signIn.postValue(State.OnSuccess(true))
        }, {
            _signIn.postValue(State.OnError(it.message))
        })
    }

    fun getSignInState(): LiveData<State<Boolean>> = _signIn

}