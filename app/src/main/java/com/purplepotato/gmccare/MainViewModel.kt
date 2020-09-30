package com.purplepotato.gmccare

import androidx.lifecycle.ViewModel

class MainViewModel(private val fbAuth : FirebaseAuthentication): ViewModel() {
    fun signOut(){
        fbAuth.signOut()
    }
}