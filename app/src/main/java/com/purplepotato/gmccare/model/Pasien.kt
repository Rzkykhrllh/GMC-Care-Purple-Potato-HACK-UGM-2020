package com.purplepotato.gmccare.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pasien(
 var nama: String = "",
 var nik: String ="",
 var no_antrian : String ="",
 var status: String = "WAITING"
) : Parcelable {

}