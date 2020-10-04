package com.purplepotato.gmccareadmin.fragment

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.purplepotato.gmccareadmin.CancelTimerActivity
import com.purplepotato.gmccareadmin.ConfirmationActivity
import com.purplepotato.gmccareadmin.Pasien
import com.purplepotato.gmccareadmin.R
import kotlinx.android.synthetic.main.fragment_ruangan.*
import java.util.*

class RuanganFragment : Fragment() {

    private var timeLeftInMillis = 0L
    var timer1 = timer()
    var timer2 = timer()
    var timer3 = timer()
    var timer4 = timer()
    var timer5 = timer()
    var timer6 = timer()

    var pasien1 = Pasien()
    var pasien2 = Pasien()
    var pasien3 = Pasien()
    var pasien4 = Pasien()
    var pasien5 = Pasien()
    var pasien6 = Pasien()

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_ruang1.setOnClickListener {
            var intent : Intent = Intent(context, CancelTimerActivity::class.java)
                .putExtra("ruang","1")
                .putExtra("nama", "${pasien1.nama}")
                .putExtra("nik", "${pasien1.nik}")
                .putExtra("no", "${pasien1.no_antrian}")
                .putExtra("status", "${pasien1.status}")
            startActivity(intent)
        }
        btn_ruang2.setOnClickListener {
            var intent : Intent = Intent(context, CancelTimerActivity::class.java)
                .putExtra("ruang","2")
                .putExtra("nama", "${pasien2.nama}")
                .putExtra("nik", "${pasien2.nik}")
                .putExtra("no", "${pasien2.no_antrian}")
                .putExtra("status", "${pasien2.status}")
            startActivity(intent)
        }
        btn_ruang3.setOnClickListener {
            var intent : Intent = Intent(context, CancelTimerActivity::class.java)
                .putExtra("ruang","3")
                .putExtra("nama", "${pasien3.nama}")
                .putExtra("nik", "${pasien3.nik}")
                .putExtra("no", "${pasien3.no_antrian}")
                .putExtra("status", "${pasien3.status}")
            startActivity(intent)
        }
        btn_ruang4.setOnClickListener {
            var intent : Intent = Intent(context, CancelTimerActivity::class.java)
                .putExtra("ruang","4")
                .putExtra("nama", "${pasien4.nama}")
                .putExtra("nik", "${pasien4.nik}")
                .putExtra("no", "${pasien4.no_antrian}")
                .putExtra("status", "${pasien4.status}")
            startActivity(intent)
        }
        btn_ruang1.setOnClickListener {
            var intent : Intent = Intent(context, CancelTimerActivity::class.java)
                .putExtra("ruang","1")
                .putExtra("nama", "${pasien5.nama}")
                .putExtra("nik", "${pasien5.nik}")
                .putExtra("no", "${pasien5.no_antrian}")
                .putExtra("status", "${pasien5.status}")
            startActivity(intent)
        }
        btn_ruang6.setOnClickListener {
            var intent : Intent = Intent(context, CancelTimerActivity::class.java)
                .putExtra("ruang","6")
                .putExtra("nama", "${pasien6.nama}")
                .putExtra("nik", "${pasien6.nik}")
                .putExtra("no", "${pasien6.no_antrian}")
                .putExtra("status", "${pasien6.status}")
            startActivity(intent)
        }
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
                                pasien1 = data
                                Log.d("nomor ruang 1", "$nomor")
                            }

                        }
                        num_room_1.text = nomor.no_antrian
                        tv_nama1.text = nomor.nama
                        tv_nik1.text = nomor.nik

                        Log.d("ruang status", "${nomor.status.toLong()}")

                        if (nomor.status.toLong() != -1L){
                            tv_timer1.visibility  = View.VISIBLE
                            timer1.setTimer(nomor.status.toLong(), tv_timer1)
                        } else{
                            // menyembunyikan timer karena pasien sudah datang
                            tv_timer1.visibility  = View.INVISIBLE
                        }

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
                                pasien2 = data
                                Log.d("nomor ruang 2", "$nomor")
                            }

                        }
                        num_room_2.text = nomor.no_antrian
                        tv_nama2.text = nomor.nama
                        tv_nik2.text = nomor.nik

                        if (nomor.status.toLong() != -1L){
                            tv_timer2.visibility  = View.VISIBLE
                            timer2.setTimer(nomor.status.toLong(), tv_timer2)
                        } else{
                            // menyembunyikan timer karena pasien sudah datang
                            tv_timer2.visibility  = View.INVISIBLE
                        }
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
                                pasien3 = data
                                Log.d("nomor ruang 3", "$nomor")
                            }

                        }
                        num_room_3.text = nomor.no_antrian
                        tv_nama3.text = nomor.nama
                        tv_nik3.text = nomor.nik

                        if (nomor.status.toLong() != -1L){
                            tv_timer3.visibility  = View.VISIBLE
                            timer3.setTimer(nomor.status.toLong(), tv_timer3)
                        } else{
                            // menyembunyikan timer karena pasien sudah datang
                            tv_timer3.visibility  = View.INVISIBLE
                        }
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
                                pasien4 = data
                                Log.d("nomor ruang 4", "$nomor")
                            }

                        }
                        num_room_4.text = nomor.no_antrian
                        tv_nama4.text = nomor.nama
                        tv_nik4.text = nomor.nik

                        if (nomor.status.toLong() != -1L){
                            tv_timer4.visibility  = View.VISIBLE
                            timer4.setTimer(nomor.status.toLong(), tv_timer4)
                        } else{
                            // menyembunyikan timer karena pasien sudah datang
                            tv_timer4.visibility  = View.INVISIBLE
                        }
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
                                pasien5 =data
                                Log.d("nomor ruang 5", "$nomor")
                            }

                        }
                        num_room_5.text = nomor.no_antrian
                        tv_nama5.text = nomor.nama
                        tv_nik5.text = nomor.nik

                        if (nomor.status.toLong() != -1L){
                            tv_timer5.visibility  = View.VISIBLE
                            timer5.setTimer(nomor.status.toLong(), tv_timer5)
                        } else{
                            // menyembunyikan timer karena pasien sudah datang
                            tv_timer5.visibility  = View.INVISIBLE
                        }
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
                                pasien6 = data
                                Log.d("nomor ruang 6", "$nomor")
                            }

                        }
                        num_room_6.text = nomor.no_antrian
                        tv_nama6.text = nomor.nama
                        tv_nik6.text = nomor.nik

                        if (nomor.status.toLong() != -1L){
                            tv_timer6.visibility  = View.VISIBLE
                            timer6.setTimer(nomor.status.toLong(), tv_timer6)
                        } else{
                            // menyembunyikan timer karena pasien sudah datang
                            tv_timer6.visibility  = View.INVISIBLE
                        }
                    }
                    override fun onCancelled(p0: DatabaseError) {
                        Toast.makeText(activity, "Nomor gagal diambil", Toast.LENGTH_SHORT)
                    }
                }
            )

    }

}

class timer(){

    private var timeLeftInMillis = 0L
    private var state_ruangan = false


    fun setTimer(endTime : Long, teks_timer : TextView) {

        val timer = object : CountDownTimer(endTime - System.currentTimeMillis() , 1_000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                updateCountDownText(teks_timer)
            }

            override fun onFinish() {
                teks_timer.text = "PASIEN TIDAK DATANG"
            }
        }.start()
    }

    private fun updateCountDownText(teks_timer : TextView) {
        val minutes = (timeLeftInMillis / 1000 / 60).toInt()
        val second = ((timeLeftInMillis / 1000) % 60).toInt()

        val timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, second)

            Log.d("menit status", "$minutes")
        if (minutes > 10){
            teks_timer.text = "Pasien sudah datang"
        } else
            teks_timer.text = timeLeftFormatted
    }

}

