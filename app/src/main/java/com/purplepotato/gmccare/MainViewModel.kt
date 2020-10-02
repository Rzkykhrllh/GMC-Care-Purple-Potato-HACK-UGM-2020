package com.purplepotato.gmccare

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(private val fbAuth : FirebaseAuthentication): ViewModel() {
    private val _resetPasswordEmail = MutableLiveData<State<Boolean>>()

    fun resetPasswordEmail(email: String) = viewModelScope.launch {
        _resetPasswordEmail.postValue(State.OnLoading())

        fbAuth.sendPasswordResetEmail(email, {
            _resetPasswordEmail.postValue(State.OnSuccess(true))
        }, {
            _resetPasswordEmail.postValue(State.OnError(it.message))
        })
    }

    fun getResetPasswordEmailState(): LiveData<State<Boolean>> =_resetPasswordEmail

    fun signOut() = viewModelScope.launch {
        fbAuth.signOut()
    }
}