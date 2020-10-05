package com.purplepotato.gmccareadmin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
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
        adapter.addFragment(RuanganFragment(), "Ruangan")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
    }


}
