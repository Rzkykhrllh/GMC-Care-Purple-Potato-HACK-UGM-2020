package com.purplepotato.gmccare.service

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
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

    fun writeUserPhoto(url: String) {
        database.child("users").child(userId).child("photo_url").setValue(url)
    }

    fun getUserProfile(onSuccess: (User) -> Unit, onFailure: (String) -> Unit) {
        database.child("users").child(userId).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.getValue(User::class.java)
                user?.let {
                    onSuccess(user)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                onFailure(error.message)
            }

        })
    }

}