package com.purplepotato.gmccareadmin.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.purplepotato.gmccareadmin.Pasien
import com.purplepotato.gmccareadmin.R
import kotlinx.android.synthetic.main.fragment_ruangan.*

class RuanganFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        updateRuang1()
        updateRuang2()
        updateRuang3()
        updateRuang4()
        updateRuang5()
        updateRuang6()


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ruangan, container, false)
    }

    private fun updateRuang1(){
        lateinit var nomor : Pasien
        FirebaseDatabase
            .getInstance()
            .getReference("RUANG_1").addValueEventListener(
                object : ValueEventListener {
                    override fun onDataChange(p0: DataSnapshot) {


                        for (child in p0.children) {
                            val data = child.getValue(Pasien::class.java)
                            Log.d("cek nomor", "aye aye")

                            if (data != null) {
                                nomor = data
                                Log.d("nomor ruang 1", "$nomor")
                            }

                        }
                        num_room_1.text = nomor.no_antrian
                        tv_nama1.text = nomor.nama
                        tv_nik1.text = nomor.nik
                    }
                    override fun onCancelled(p0: DatabaseError) {
                        Toast.makeText(activity, "Nomor gagal diambil", Toast.LENGTH_SHORT)
                    }
                }
            )

    }

    private fun updateRuang2(){
        lateinit var nomor : Pasien
        FirebaseDatabase
            .getInstance()
            .getReference("RUANG_2").addValueEventListener(
                object : ValueEventListener {
                    override fun onDataChange(p0: DataSnapshot) {


                        for (child in p0.children) {
                            val data = child.getValue(Pasien::class.java)
                            Log.d("cek nomor", "aye aye")

                            if (data != null) {
                                nomor = data
                                Log.d("nomor ruang 2", "$nomor")
                            }

                        }
                        num_room_2.text = nomor.no_antrian
                        tv_nama2.text = nomor.nama
                        tv_nik2.text = nomor.nik
                    }
                    override fun onCancelled(p0: DatabaseError) {
                        Toast.makeText(activity, "Nomor gagal diambil", Toast.LENGTH_SHORT)
                    }
                }
            )

    }

    private fun updateRuang3(){
        lateinit var nomor : Pasien
        FirebaseDatabase
            .getInstance()
            .getReference("RUANG_3").addValueEventListener(
                object : ValueEventListener {
                    override fun onDataChange(p0: DataSnapshot) {


                        for (child in p0.children) {
                            val data = child.getValue(Pasien::class.java)
                            Log.d("cek nomor", "aye aye")

                            if (data != null) {
                                nomor = data
                                Log.d("nomor ruang 3", "$nomor")
                            }

                        }
                        num_room_3.text = nomor.no_antrian
                        tv_nama3.text = nomor.nama
                        tv_nik3.text = nomor.nik
                    }
                    override fun onCancelled(p0: DatabaseError) {
                        Toast.makeText(activity, "Nomor gagal diambil", Toast.LENGTH_SHORT)
                    }
                }
            )

    }

    private fun updateRuang4(){
        lateinit var nomor : Pasien
        FirebaseDatabase
            .getInstance()
            .getReference("RUANG_4").addValueEventListener(
                object : ValueEventListener {
                    override fun onDataChange(p0: DataSnapshot) {


                        for (child in p0.children) {
                            val data = child.getValue(Pasien::class.java)
                            Log.d("cek nomor", "aye aye")

                            if (data != null) {
                                nomor = data
                                Log.d("nomor ruang 4", "$nomor")
                            }

                        }
                        num_room_4.text = nomor.no_antrian
                        tv_nama4.text = nomor.nama
                        tv_nik4.text = nomor.nik
                    }
                    override fun onCancelled(p0: DatabaseError) {
                        Toast.makeText(activity, "Nomor gagal diambil", Toast.LENGTH_SHORT)
                    }
                }
            )

    }

    private fun updateRuang5(){
        lateinit var nomor : Pasien
        FirebaseDatabase
            .getInstance()
            .getReference("RUANG_5").addValueEventListener(
                object : ValueEventListener {
                    override fun onDataChange(p0: DataSnapshot) {


                        for (child in p0.children) {
                            val data = child.getValue(Pasien::class.java)
                            Log.d("cek nomor", "aye aye")

                            if (data != null) {
                                nomor = data
                                Log.d("nomor ruang 5", "$nomor")
                            }

                        }
                        num_room_5.text = nomor.no_antrian
                        tv_nama5.text = nomor.nama
                        tv_nik5.text = nomor.nik
                    }
                    override fun onCancelled(p0: DatabaseError) {
                        Toast.makeText(activity, "Nomor gagal diambil", Toast.LENGTH_SHORT)
                    }
                }
            )

    }

    private fun updateRuang6(){
        lateinit var nomor : Pasien
        FirebaseDatabase
            .getInstance()
            .getReference("RUANG_6").addValueEventListener(
                object : ValueEventListener {
                    override fun onDataChange(p0: DataSnapshot) {


                        for (child in p0.children) {
                            val data = child.getValue(Pasien::class.java)
                            Log.d("cek nomor", "aye aye")

                            if (data != null) {
                                nomor = data
                                Log.d("nomor ruang 6", "$nomor")
                            }

                        }
                        num_room_6.text = nomor.no_antrian
                        tv_nama6.text = nomor.nama
                        tv_nik6.text = nomor.nik
                    }
                    override fun onCancelled(p0: DatabaseError) {
                        Toast.makeText(activity, "Nomor gagal diambil", Toast.LENGTH_SHORT)
                    }
                }
            )

    }

}