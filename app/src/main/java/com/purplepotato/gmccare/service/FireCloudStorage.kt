package com.purplepotato.gmccare.service

import android.graphics.Bitmap
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream

class FireCloudStorage {

    private val storage by lazy {
        FirebaseStorage.getInstance()
    }

    private val userId by lazy {
        FirebaseAuth.getInstance().currentUser!!.uid
    }

    private val storageRef by lazy {
        storage.reference.child("images/$userId.png")
    }

    fun uploadUserImage(bitmap: Bitmap, onSuccess: (String) -> Unit, onFailure: (String) -> Unit) {
        val byteArray = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray)
        val data = byteArray.toByteArray()

        val uploadTask = storageRef.putBytes(data)
        uploadTask.addOnSuccessListener {
            storageRef.downloadUrl.addOnSuccessListener { uri ->
                onSuccess(uri.toString())
            }
        }.addOnFailureListener {
            onFailure(it.localizedMessage as String)
            Log.d("error",  it.printStackTrace().toString())
        }

    }


}