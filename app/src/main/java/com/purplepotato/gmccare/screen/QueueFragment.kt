package com.purplepotato.gmccare.screen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.purplepotato.gmccare.R
import com.purplepotato.gmccare.model.Pasien
import kotlinx.android.synthetic.main.fragment_queue.*

class QueueFragment : Fragment(), View.OnClickListener {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_queue, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        drawerLayout = requireActivity().findViewById(R.id.drawer_layout)

        btnOpenDrawerInQueue.setOnClickListener(this)

        Log.d("queue", "udah di qeue fragment")
        updateRuang1()
        updateRuang2()
        updateRuang3()
        updateRuang4()
        updateRuang5()
        updateRuang6()


    }

    private fun updateRuang1(){
        var nomor : String = ""
        FirebaseDatabase
            .getInstance()
            .getReference("RUANG_1").addValueEventListener(
                object : ValueEventListener {
                    override fun onDataChange(p0: DataSnapshot) {


                        for (child in p0.children) {
                            val data = child.getValue(Pasien::class.java)
                            Log.d("cek nomor", "aye aye")

                            if (data != null) {
                                nomor = data.no_antrian
                                Log.d("nomor ruang 1", "$nomor")
                            }

                        }
                        num_room_1.text = nomor
                    }
                    override fun onCancelled(p0: DatabaseError) {
                        Toast.makeText(activity, "Nomor gagal diambil", Toast.LENGTH_SHORT)
                    }
                }
            )

    }

    private fun updateRuang2(){
        var nomor : String = ""
        FirebaseDatabase
            .getInstance()
            .getReference("RUANG_2").addValueEventListener(
                object : ValueEventListener {
                    override fun onDataChange(p0: DataSnapshot) {


                        for (child in p0.children) {
                            val data = child.getValue(Pasien::class.java)
                            Log.d("cek nomor", "aye aye")

                            if (data != null) {
                                nomor = data.no_antrian
                                Log.d("nomor ruang 2", "$nomor")
                            }

                        }
                        num_room_2.text = nomor
                    }
                    override fun onCancelled(p0: DatabaseError) {
                        Toast.makeText(activity, "Nomor gagal diambil", Toast.LENGTH_SHORT)
                    }
                }
            )

    }

    private fun updateRuang3(){
        var nomor : String = ""
        FirebaseDatabase
            .getInstance()
            .getReference("RUANG_3").addValueEventListener(
                object : ValueEventListener {
                    override fun onDataChange(p0: DataSnapshot) {


                        for (child in p0.children) {
                            val data = child.getValue(Pasien::class.java)
                            Log.d("cek nomor", "aye aye")

                            if (data != null) {
                                nomor = data.no_antrian
                                Log.d("nomor ruang 3", "$nomor")
                            }

                        }
                        num_room_3.text = nomor
                    }
                    override fun onCancelled(p0: DatabaseError) {
                        Toast.makeText(activity, "Nomor gagal diambil", Toast.LENGTH_SHORT)
                    }
                }
            )

    }

    private fun updateRuang4(){
        var nomor : String = ""
        FirebaseDatabase
            .getInstance()
            .getReference("RUANG_4").addValueEventListener(
                object : ValueEventListener {
                    override fun onDataChange(p0: DataSnapshot) {


                        for (child in p0.children) {
                            val data = child.getValue(Pasien::class.java)
                            Log.d("cek nomor", "aye aye")

                            if (data != null) {
                                nomor = data.no_antrian
                                Log.d("nomor ruang 4", "$nomor")
                            }

                        }
                        num_room_4.text = nomor
                    }
                    override fun onCancelled(p0: DatabaseError) {
                        Toast.makeText(activity, "Nomor gagal diambil", Toast.LENGTH_SHORT)
                    }
                }
            )

    }

    private fun updateRuang5(){
        var nomor : String = ""
        FirebaseDatabase
            .getInstance()
            .getReference("RUANG_5").addValueEventListener(
                object : ValueEventListener {
                    override fun onDataChange(p0: DataSnapshot) {


                        for (child in p0.children) {
                            val data = child.getValue(Pasien::class.java)
                            Log.d("cek nomor", "aye aye")

                            if (data != null) {
                                nomor = data.no_antrian
                                Log.d("nomor ruang 5", "$nomor")
                            }

                        }
                        num_room_5.text = nomor
                    }
                    override fun onCancelled(p0: DatabaseError) {
                        Toast.makeText(activity, "Nomor gagal diambil", Toast.LENGTH_SHORT)
                    }
                }
            )

    }

    private fun updateRuang6(){
        var nomor : String = ""
        FirebaseDatabase
            .getInstance()
            .getReference("RUANG_6").addValueEventListener(
                object : ValueEventListener {
                    override fun onDataChange(p0: DataSnapshot) {


                        for (child in p0.children) {
                            val data = child.getValue(Pasien::class.java)
                            Log.d("cek nomor", "aye aye")

                            if (data != null) {
                                nomor = data.no_antrian
                                Log.d("nomor ruang 6", "$nomor")
                            }

                        }
                        num_room_6.text = nomor
                    }
                    override fun onCancelled(p0: DatabaseError) {
                        Toast.makeText(activity, "Nomor gagal diambil", Toast.LENGTH_SHORT)
                    }
                }
            )

    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btnOpenDrawerInQueue -> {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }
    }

}