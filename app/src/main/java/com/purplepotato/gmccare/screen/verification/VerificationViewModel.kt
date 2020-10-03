package com.purplepotato.gmccare.screen.verification

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.purplepotato.gmccare.State
import com.purplepotato.gmccare.service.FireCloudStorage
import com.purplepotato.gmccare.service.RealtimeDatabase
import kotlinx.coroutines.launch

class VerificationViewModel(private val fbStorage: FireCloudStorage,private val fbDatabase: RealtimeDatabase) : ViewModel() {

    private val _uploadUserImage = MutableLiveData<State<String>>()

    fun uploadImage(bitmap: Bitmap) = viewModelScope.launch {
        _uploadUserImage.postValue(State.OnLoading())
        fbStorage.uploadUserImage(bitmap, {
            fbDatabase.writeUserPhoto(it)
            Log.d("url", it)
            _uploadUserImage.postValue(State.OnSuccess("Success"))
        }, {
            _uploadUserImage.postValue(State.OnError(it))
        })
    }

    fun uploadImageState() : LiveData<State<String>> = _uploadUserImage
}