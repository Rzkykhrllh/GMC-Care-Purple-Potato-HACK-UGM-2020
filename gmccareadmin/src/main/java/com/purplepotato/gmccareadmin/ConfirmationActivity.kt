package com.purplepotato.gmccareadmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_confirmation.*

class ConfirmationActivity : AppCompatActivity() {

    lateinit var nama : String
    lateinit var no : String
    lateinit var nik : String
    lateinit var status : String
    var ruang : String =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)

        getData()
        nomor.text = no

        btn_panggil.setOnClickListener{
            ruang = et_nomor.text.toString()

            if (ruang==""){
                et_nomor.error = "Masukkan nomor ruangan"
            }

            keRuang()
        }


    }

    private fun keRuang() {
        //fungsi untuk update nomor di database, setelah kita ambil
        Log.d("Cast berhasil", "Masuk sini gan")

        var map = mutableMapOf<String, String>()

        map["nama"] = nama
        map["no_antrian"] = no
        map["status"] = "CALLING"
        map["nik"] = nik

        //Update Ruangan
        FirebaseDatabase.getInstance().reference //update saldo
            .child("RUANG_" + "${ruang}")
            .child("0")
            .updateChildren(map as Map<String, Any>)

        Log.d("berhasil oper data", "ruang 1")

        //Hapus dari antrian
        FirebaseDatabase.getInstance()
            .getReference("Antrian")
            .child("${no}")
            .removeValue()

        //Taruh di calling
        map["status"] = ruang.toString()
        FirebaseDatabase.getInstance().reference //update saldo
            .child("CALLING")
            .updateChildren(map as Map<String, Any>)

        Log.d("berhasil hapus data", "ruang 1")
        //startActivity(Intent(context, dmmyactivity::class.java))
        Toast.makeText(this, "Berhasil memanggil Pasien ke ruang $ruang", Toast.LENGTH_LONG).show()
    }

    private fun getData() {
        nama = intent.getStringExtra("nama")
        no = intent.getStringExtra("no")
        nik = intent.getStringExtra("nik")
        status = intent.getStringExtra("status")

    }
}