package com.purplepotato.gmccare.screen.verification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.purplepotato.gmccare.service.FireCloudStorage
import com.purplepotato.gmccare.service.RealtimeDatabase

class VerificationViewModelFactory(
    private val fbStorage: FireCloudStorage,
    private val fbDatabase: RealtimeDatabase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return VerificationViewModel(fbStorage, fbDatabase) as T
    }

}