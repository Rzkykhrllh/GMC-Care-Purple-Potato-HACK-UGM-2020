package com.purplepotato.gmccare

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.*
import com.purplepotato.gmccare.Airu.Model.Nomor
import com.purplepotato.gmccare.Airu.Model.Pasien
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class HomeFragment : Fragment(), View.OnClickListener {

    private lateinit var drawerLayout: DrawerLayout
    var number = "0"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        drawerLayout = requireActivity().findViewById(R.id.drawer_layout)

        btnOpenDrawerInHome.setOnClickListener(this)

        btnToQueue.setOnClickListener {
            Log.d("button", "queue")
            val pasien = makeUser()

            CoroutineScope(IO).launch {
                takeNumber({
                    Log.d("Nomornya fathur : ", "$number dan $it")
                    number = it
                    ToQueue(number, pasien)
                })
            }
            updateNumber()
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnOpenDrawerInHome -> {
                drawerLayout.openDrawer(GravityCompat.START)
                Log.d("button", "menu")
            }
        }
    }

    fun makeUser(): Pasien {
        //Fungsi untuk ngambil data pasien dari data yang sudah di daftarkan
        return Pasien("Airu", "12345", "WAITING")
    }


    private suspend fun takeNumber(onResult: (String) -> Unit): String {
        //fungsi untuk mengambil nomor antrian dari database
        var nomor = "-2"

        FirebaseDatabase
            .getInstance()
            .getReference("Nomor").addValueEventListener(
                object : ValueEventListener {
                    override fun onDataChange(p0: DataSnapshot) {

                        for (child in p0.children) {
                            val data = child.getValue(Nomor::class.java)
                            Log.d("cek nomor", "aye aye")

                            if (data != null) {
                                onResult(data.no_antrian)
                                Log.d("Ambil nomor", "$nomor")
                            }
                        }
                    }
                    override fun onCancelled(p0: DatabaseError) {
                        Toast.makeText(activity, "Nomor gagal diambil", Toast.LENGTH_SHORT)
                    }
                }
            )
        Log.d("nomor akhir", "$nomor")
        return nomor
    }

    private fun updateNumber() {
        //fungsi untuk update nomor di database, setelah kita ambil
    }

    private fun ToQueue(no: String, pasien: Pasien) {
        //fungsi untuk menambahkan datadiri ke antiran

        FirebaseDatabase
            .getInstance()
            .getReference("Antrian")
            .child(no.toString())
            .setValue(pasien)
            .addOnSuccessListener {
                Toast.makeText(activity, "Silahkan tunggu nomormu dipanggil", Toast.LENGTH_LONG)
                Log.d("antri", "Berhasil antri dengan nomor $no")
            }
    }

}