package com.purplepotato.gmccare.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(
    var name: String? = "",
    var nik: String? = "0",
    var birthDate:Long? = 0L,
    var email: String? = ""
)