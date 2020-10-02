package com.purplepotato.gmccare.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.purplepotato.gmccare.service.FirebaseAuthentication

class LoginViewModelFactory(private val fbAuth: FirebaseAuthentication): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
       return LoginViewModel(fbAuth) as T
    }
}