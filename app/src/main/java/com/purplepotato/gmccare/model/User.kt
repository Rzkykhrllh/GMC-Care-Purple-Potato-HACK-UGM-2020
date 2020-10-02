package com.purplepotato.gmccare.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(
    var name: String?,
    var nik: String,
    var birthDate:Long?,
    var email: String?
)