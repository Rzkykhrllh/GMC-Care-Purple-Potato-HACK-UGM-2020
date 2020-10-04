package com.purplepotato.gmccare.screen.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.purplepotato.gmccare.R
import com.purplepotato.gmccare.model.Nomor
import com.purplepotato.gmccare.model.Pasien
import com.purplepotato.gmccare.pref.Preferences
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeFragment : Fragment(), View.OnClickListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var sharedPreferences: Preferences
    var number = "0"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences = Preferences(requireContext())

        drawerLayout = requireActivity().findViewById(R.id.drawer_layout)

        btnOpenDrawerInHome.setOnClickListener(this)
        btnToQueue.setOnClickListener(this)
        btnCancelQueue.setOnClickListener(this)

        isAlreadyHaveQueueNumber()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnOpenDrawerInHome -> {
                drawerLayout.openDrawer(GravityCompat.START)
                Log.d("button", "menu")
            }

            R.id.btnToQueue -> {
                Log.d("button", "queue")
                val pasien = makeUser()

                takeNumber()
            }

            R.id.btnCancelQueue -> {
                // hapus antrian
                removeData(sharedPreferences.getUserQueueNumber().toInt()) //menghapus data user dari database
                sharedPreferences.setIsQueued(false)
                sharedPreferences.setUserQueueNumber(-1)
                isAlreadyHaveQueueNumber()
            }
        }
    }

    fun makeUser(): Pasien {
        //Fungsi untuk ngambil data pasien dari data yang sudah di daftarkan
        return Pasien("Airu", "12345", "WAITING")
    }


    private fun takeNumber() {
        //fungsi untuk mengambil nomor antrian dari database
        var nomor = "-2"

        FirebaseDatabase
            .getInstance()
            .getReference("Nomor").addListenerForSingleValueEvent(
                object : ValueEventListener {
                    override fun onDataChange(p0: DataSnapshot) {


                        for (child in p0.children) {
                            val data = child.getValue(Nomor::class.java)
                            Log.d("cek nomor", "aye aye")

                            if (data != null) {
                                nomor = data.no_antrian
                                Log.d("Ambil nomor", "$nomor")

                                ToQueue(nomor, Pasien("Airu", "12345", "$nomor", "WAITING"))
                                sharedPreferences.setIsQueued(true)
                                sharedPreferences.setUserQueueNumber(nomor.toInt())
                                isAlreadyHaveQueueNumber()
                                updateNumber(nomor.toInt())

                            }
                        }
                    }
                    override fun onCancelled(p0: DatabaseError) {
                        Toast.makeText(activity, "Nomor gagal diambil", Toast.LENGTH_SHORT).show()
                    }
                }
            )
        Log.d("nomor akhir", "$nomor")
    }

    private fun updateNumber(number_now : Int) {
        //fungsi untuk update nomor di database, setelah kita ambil

        var map = mutableMapOf<String, String>()
        var num = number_now + 1
        map["no_antrian"] = num.toString()


        FirebaseDatabase.getInstance().reference //update saldo
            .child("Nomor")
            .child("0")
            .updateChildren(map as Map<String, Any>)

        Log.d("update nomor", "$num")

        Log.d("pindah kuy", "mau pindah ke queue fragment nomor")

        CoroutineScope(Main).launch{
            delay(2000)
            findNavController().navigate(R.id.toQueueFragment)
        }
    }

    private fun ToQueue(no: String, pasien: Pasien) {
        //fungsi untuk menambahkan data diri ke antiran

        FirebaseDatabase
            .getInstance()
            .getReference("Antrian")
            .child(no.toString())
            .setValue(pasien)
            .addOnSuccessListener {
                Toast.makeText(activity, "Silahkan tunggu nomormu dipanggil", Toast.LENGTH_LONG).show()
                sharedPreferences.setUserQueueNumber(pasien.no_antrian.toInt())
                Log.d("antri", "Berhasil antri dengan nomor $no")
            }

    }

    private fun isAlreadyHaveQueueNumber(){
        val status = sharedPreferences.getIsQueued()
        if (status){
            btnToQueue.visibility = View.GONE
            btnCancelQueue.visibility = View.VISIBLE
        } else {
            btnToQueue.visibility = View.VISIBLE
            btnCancelQueue.visibility = View.GONE
        }
    }

    private fun removeData(no : Int){
        FirebaseDatabase.getInstance()
            .getReference("Antrian")
            .child("${no}")
            .removeValue()
    }

}