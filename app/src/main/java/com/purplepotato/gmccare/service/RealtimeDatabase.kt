package com.purplepotato.gmccare.service

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.purplepotato.gmccare.model.User

class RealtimeDatabase {

    private val fbDatabase by lazy {
        FirebaseDatabase.getInstance()
    }

    private val database by lazy {
        fbDatabase.reference
    }

    private val userId by lazy {
        FirebaseAuth.getInstance().currentUser!!.uid
    }

    fun writeUserData(name: String, birthDate: Long, nik: String, email: String) {
        val user = User(name, nik, birthDate, email)
        database.child("users").child(userId).setValue(user)
    }

}