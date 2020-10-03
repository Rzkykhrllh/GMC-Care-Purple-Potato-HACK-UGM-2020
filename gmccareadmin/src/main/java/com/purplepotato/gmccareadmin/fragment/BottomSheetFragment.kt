package com.purplepotato.gmccareadmin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.purplepotato.gmccareadmin.R
import kotlinx.android.synthetic.main.bottomsheet_fragment.*

class BottomSheetFragment : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         return inflater.inflate(R.layout.bottomsheet_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnPanggil.setOnClickListener{
            Toast.makeText(context, "Berhasil Mencet", Toast.LENGTH_LONG)
        }
    }
}