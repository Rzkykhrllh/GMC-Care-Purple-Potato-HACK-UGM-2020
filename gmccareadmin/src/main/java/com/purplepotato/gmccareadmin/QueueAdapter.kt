package com.example.movticket.Home.Dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.ValueEventListener
import com.purplepotato.gmccareadmin.Pasien
import com.purplepotato.gmccareadmin.R

class QueueAdapter(private var data: List<Pasien>,
                        private val listener: (Pasien) -> Unit)
    : RecyclerView.Adapter<QueueAdapter.ViewHolder>() {

    lateinit var contextAdapter: Context

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val tvNo: TextView = view.findViewById(R.id.tvNomor)
        val tvNama: TextView = view.findViewById(R.id.tvNama)
        val tvNik: TextView = view.findViewById(R.id.tvNik)
        //val tvStatus : ImageView = view.findViewById(R.id.img_poster_now)

        fun bindItem(data:Pasien, listener:(Pasien) -> Unit, context: Context){
            tvNama.setText(data.nama)
            tvNo.setText(data.no_antrian)
            tvNik.setText(data.nik)

            itemView.setOnClickListener {
                listener(data)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QueueAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView = layoutInflater.inflate(R.layout.row_item_queue, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(data[position], listener, contextAdapter)
    }

}