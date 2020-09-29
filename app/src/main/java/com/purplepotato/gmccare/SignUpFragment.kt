package com.purplepotato.gmccare

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_sign_up.*

class SignUpFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnSignUp.setOnClickListener(this)
        btnBackToLoginFragment.setOnClickListener(this)
        tvBackToLoginFragment.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btnSignUp -> {
                //code for sign up to firebase
                findNavController().navigate(R.id.toVerificationFragment)
            }

            R.id.btnBackToLoginFragment -> requireActivity().onBackPressed()
            R.id.tvBackToLoginFragment -> requireActivity().onBackPressed()
        }
    }
}