package com.purplepotato.gmccare.screen.sign_up

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.purplepotato.gmccare.State
import com.purplepotato.gmccare.service.FirebaseAuthentication
import com.purplepotato.gmccare.service.RealtimeDatabase
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val fbAuth: FirebaseAuthentication,
    private val fbDatabase: RealtimeDatabase
) : ViewModel() {

    private val _signUp = MutableLiveData<State<Boolean>>()

    fun createUserWithEmailAndPassword(
        email: String,
        password: String,
        name: String,
        birthDate: Long,
        nik: String
    ) = viewModelScope.launch {
        _signUp.postValue(State.OnLoading())
        fbAuth.createUser(email, password, {
            _signUp.postValue(State.OnSuccess(true))
            writeUserData(name, birthDate, nik, email)
        }, {
            _signUp.postValue(State.OnError(it.message))
        })
    }

    fun getSignUpState(): LiveData<State<Boolean>> = _signUp

    private fun writeUserData(name: String, birthDate: Long, nik: String, email: String) {
        fbDatabase.writeUserData(name, birthDate, nik, email)
    }

}