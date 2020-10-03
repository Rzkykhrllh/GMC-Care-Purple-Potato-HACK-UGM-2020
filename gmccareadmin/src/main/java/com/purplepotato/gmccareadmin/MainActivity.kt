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
import com.purplepotato.gmccareadmin.fragment.Antrian
import com.purplepotato.gmccareadmin.fragment.RuanganFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var dataList : ArrayList<Pasien> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseApp.initializeApp(this)
        setUpTabs()
    }

    private fun setUpTabs(){
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(Antrian(), "Antrian Pasien")
        adapter.addFragment(RuanganFragment(), "Ruanan")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
    }


}
