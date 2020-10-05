package com.purplepotato.gmccareadmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_cancel_timer.*

class CancelTimerActivity : AppCompatActivity() {

    var nama: String = ""
    var no: String = ""
    var nik: String = ""
    var status: String =""
    var ruang: String = ""
    var daftar = listOf<String>("Ruang 1", "Ruang 2", "Ruang 3", "Ruang 4", "Ruang 5", "Ruang 6")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cancel_timer)

        getData()


        nomor.text = no
        teks3.text = "sudah datang ke ruang $ruang"

        btn_ya.setOnClickListener {

            matikanTimer()
            onBackPressed()
        }

    }

    private fun matikanTimer() {
        var map = mutableMapOf<String, String>()

        map["nama"] = nama
        map["no_antrian"] = no
        map["status"] = "-1"
        map["nik"] = nik

        //Update Ruangan
        FirebaseDatabase.getInstance().reference
            .child("RUANG_" + "${ruang}")
            .child("0")
            .updateChildren(map as Map<String, Any>)
    }


    private fun getData() {
        nama = intent.getStringExtra("nama")
        no = intent.getStringExtra("no")
        nik = intent.getStringExtra("nik")
        status = intent.getStringExtra("status")
        ruang = intent.getStringExtra("ruang")


    }

}