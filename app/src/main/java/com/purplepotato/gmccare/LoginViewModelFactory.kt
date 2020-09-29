package com.purplepotato.gmccare

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class LoginViewModelFactory(private val fbAuth:FirebaseAuthentication): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
       return LoginViewModel(fbAuth) as T
    }
}