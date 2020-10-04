package com.purplepotato.gmccare.screen.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.purplepotato.gmccare.State
import com.purplepotato.gmccare.model.User
import com.purplepotato.gmccare.service.RealtimeDatabase
import kotlinx.coroutines.launch

class HomeViewModel(private val fbDatabase: RealtimeDatabase) : ViewModel() {

    private val _userProfileState = MutableLiveData<State<User>>()

    fun getUserProfile() =viewModelScope.launch {
        _userProfileState.postValue(State.OnLoading())
        fbDatabase.getUserProfile({ user ->
            _userProfileState.postValue(State.OnSuccess(user))
        }, { errorMessage ->
            _userProfileState.postValue(State.OnError(errorMessage))
        })
    }

    fun getUserProfileState(): LiveData<State<User>> = _userProfileState
}