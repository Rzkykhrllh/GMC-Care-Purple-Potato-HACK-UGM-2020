package com.purplepotato.gmccare.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.purplepotato.gmccare.service.RealtimeDatabase

class HomeViewModelFactory(private val fbDatabase: RealtimeDatabase): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(fbDatabase) as T
    }

}