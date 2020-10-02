package com.purplepotato.gmccare

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.purplepotato.gmccare.screen.login.LoginFragment
import kotlinx.android.synthetic.main.forgot_password_bottom_sheet.*

class ForgotPasswordBottomSheetDialog : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.forgot_password_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnForgotPassword.setOnClickListener {
            val email = et_email_forgot_password.text.toString().trim()

            if (email.isNotEmpty()) {
                setFragmentResult(
                    LoginFragment.forgotBottomSheetRequestKey,
                    bundleOf(LoginFragment.forgotBottomSheetBundleKey to email)
                )
            } else {
                et_email_forgot_password.error = "Field ini tidak boleh kosong"
            }
        }
    }

}