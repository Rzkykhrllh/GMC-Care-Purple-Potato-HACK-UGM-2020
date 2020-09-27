package com.purplepotato.gmccare

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
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
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btnOpenDrawerInQueue -> {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }
    }

}