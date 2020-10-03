package com.purplepotato.gmccareadmin.fragment

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movticket.Home.Dashboard.QueueAdapter
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.core.Context
import com.purplepotato.gmccareadmin.ConfirmationActivity
import com.purplepotato.gmccareadmin.Pasien
import com.purplepotato.gmccareadmin.R
import kotlinx.android.synthetic.main.bottomsheet_fragment.*
import kotlinx.android.synthetic.main.fragment_antrian.*
import kotlinx.android.synthetic.main.pop_up_dialog.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Antrian : Fragment() {

    private var dataList: ArrayList<Pasien> = ArrayList()
    lateinit var myDialog: Dialog
    val ruangan = listOf("Ruang 1", "Ruang 2", "Ruang 3", "Ruang 4", "Ruang 5", "Ruang 6")
    var pilihRuangan = ""

    lateinit var button: Button
    var bottom_sheet_fragment = BottomSheetFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_antrian, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //button = view.findViewById(R.id.btn_ok) as Button
        getdata()

    }

    private fun getdata() {
        FirebaseDatabase
            .getInstance()
            .getReference("Antrian")
            .addValueEventListener(
                object : ValueEventListener {

                    override fun onCancelled(databaseError: DatabaseError) {
                        Log.d("Error", "Gagal ambil data dari firebase")
                    }

                    override fun onDataChange(dataSnapshot: DataSnapshot) {

                        dataList.clear()
                        for (getdatasnapshot in dataSnapshot.children) {
                            var film = getdatasnapshot.getValue(Pasien::class.java)
                            dataList.add(film!!)
                        }

                        rv_queue.layoutManager = LinearLayoutManager(context) //ngeset recycler view
                        rv_queue.adapter = QueueAdapter(dataList) {
                            var data = it

                            var intent : Intent = Intent(context, ConfirmationActivity::class.java)
                                .putExtra("nama", data.nama)
                                .putExtra("no", data.no_antrian)
                                .putExtra("nik", data.nik)
                                .putExtra("status", data.status)
                            startActivity(intent)

                            /*bottom_sheet_fragment.show(childFragmentManager, "yey")
                            bottom_sheet_fragment.btnPanggil.setOnClickListener {
                                Toast.makeText(context, "Bisa mencet", Toast.LENGTH_SHORT).show()
                            }*/

                        }

                    }

                })
    }

    private fun sheet() {

    }


    private fun keRuang(data: Pasien, no_ruang: Int = 4) {
        //fungsi untuk update nomor di database, setelah kita ambil
        Log.d("Cast berhasil", "Masuk sini gan")

        var map = mutableMapOf<String, String>()

        map["nama"] = data.nama
        map["no_antrian"] = data.no_antrian
        map["status"] = "Calling"
        map["nik"] = data.nik

        FirebaseDatabase.getInstance().reference //update saldo
            .child("RUANG_" + "${no_ruang.toString()}")
            .child("0")
            .updateChildren(map as Map<String, Any>)

        Log.d("berhasil oper data", "ruang 1")

        FirebaseDatabase.getInstance()
            .getReference("Antrian")
            .child("${data.no_antrian}")
            .removeValue()

        Log.d("berhasil hapus data", "ruang 1")
        //startActivity(Intent(context, dmmyactivity::class.java))
        Toast.makeText(context, "Berhasil memanggil Pasien ke ruang 1", Toast.LENGTH_LONG).show()
    }
}