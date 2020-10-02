package com.purplepotato.gmccare

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SignUpViewModel(private val fbAuth: FirebaseAuthentication) : ViewModel() {

    private val _signUp = MutableLiveData<State<Boolean>>()

    fun createUserWithEmailAndPassword(email: String, password: String) = viewModelScope.launch {
        _signUp.postValue(State.OnLoading())
        fbAuth.createUser(email, password, {
            _signUp.postValue(State.OnSuccess(true))
        }, {
            _signUp.postValue(State.OnError(it.message))
        })
    }

    fun getSignUpState(): LiveData<State<Boolean>> = _signUp

}