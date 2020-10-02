package com.purplepotato.gmccare.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.purplepotato.gmccare.service.FirebaseAuthentication

class MainViewModelFactory(private val fbAuth: FirebaseAuthentication): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(fbAuth) as T
    }
}