package com.purplepotato.gmccare.screen.sign_up

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.purplepotato.gmccare.service.FirebaseAuthentication
import com.purplepotato.gmccare.service.RealtimeDatabase

class SignUpViewModelFactory(
    private val fbAuthentication: FirebaseAuthentication,
    private val fbDatabase: RealtimeDatabase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SignUpViewModel(fbAuthentication, fbDatabase) as T
    }

}