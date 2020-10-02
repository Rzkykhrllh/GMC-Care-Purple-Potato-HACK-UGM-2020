package com.purplepotato.gmccareadmin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movticket.Home.Dashboard.QueueAdapter
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var dataList : ArrayList<Pasien> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseApp.initializeApp(this);
        rv_queue.layoutManager =  LinearLayoutManager(this) //ngeset recycler view
        getdata()
    }

    private fun getdata() {
        FirebaseDatabase
            .getInstance()
            .getReference("Antrian").
            addValueEventListener(object : ValueEventListener {

            override fun onCancelled(databaseError: DatabaseError) {
                Log.d("Error","Gagal ambil data dari firebase")
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                dataList.clear()
                for (getdatasnapshot in dataSnapshot.children){
                    var film =  getdatasnapshot.getValue(Pasien::class.java)
                    dataList.add(film!!)
                }

                rv_queue.adapter = QueueAdapter(dataList){
                    //var intent : Intent = Intent(this, DetailActivity::class.java).putExtra("data", it)
                    //startActivity(intent)
                }

            }

        })
    }
}
