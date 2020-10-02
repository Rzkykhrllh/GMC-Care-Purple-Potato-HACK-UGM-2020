package com.purplepotato.gmccare.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.purplepotato.gmccare.R

import kotlinx.android.synthetic.main.fragment_verification.*

class VerificationFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_verification, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnAddVerificationPhoto.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnAddVerificationPhoto -> {
                //code for open camera and save the result to firebase

                val action = VerificationFragmentDirections.fromVerificationToHomeFragment()
                Navigation.findNavController(btnAddVerificationPhoto).navigate(action)
            }
        }
    }

}