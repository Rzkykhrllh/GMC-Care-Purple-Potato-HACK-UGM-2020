package com.purplepotato.gmccare.screen.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.purplepotato.gmccare.ForgotPasswordBottomSheetDialog
import com.purplepotato.gmccare.R
import com.purplepotato.gmccare.State
import com.purplepotato.gmccare.service.FirebaseAuthentication
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment(), View.OnClickListener {

    private lateinit var fbAuth: FirebaseAuth
    private lateinit var viewModel: LoginViewModel

    companion object {
        const val forgotBottomSheetRequestKey = "request_email"
        const val forgotBottomSheetBundleKey = "bottom_sheet_bundle_key"
    }

    override fun onStart() {
        super.onStart()
        fbAuth = FirebaseAuth.getInstance()
        val user = fbAuth.currentUser
        if (user != null) {
            toHomeFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fbAuthentication = FirebaseAuthentication()
        val viewModelFactory = LoginViewModelFactory(fbAuthentication)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(LoginViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnToSignUpFragment.setOnClickListener(this)
        btnLogin.setOnClickListener(this)
        tv_forgot_password.setOnClickListener(this)
        signInState()
        forgotPasswordState()
    }

    private fun signInState() {
        viewModel.getSignInState().observe(viewLifecycleOwner, { response ->
            when (response) {
                is State.OnLoading -> {
                    showLoading(true)
                }

                is State.OnSuccess -> {
                    showLoading(false)
                    toHomeFragment()
                }

                is State.OnError -> {
                    response.message?.let {
                        Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
                    }
                    showLoading(false)
                }
            }
        })
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnToSignUpFragment -> {
                findNavController().navigate(R.id.toSignUpFragment)
            }

            R.id.btnLogin -> {
                val email = et_email.text.toString().trim()
                val password = et_password.text.toString()
                var isInputValid = true

                if (email.isEmpty()) {
                    et_email.error = "Isi email anda"
                    isInputValid = false
                }

                if (password.length < 8) {
                    et_password.error = "Password anda harus lebih dari 8 karakter"
                    isInputValid = false
                }

                if (isInputValid) {
                    viewModel.emailAndPasswordSignIn(email, password)
                    et_email.setText("")
                    et_password.setText("")
                }
            }

            R.id.tv_forgot_password -> {
                val modalBottomSheet = ForgotPasswordBottomSheetDialog()
                modalBottomSheet.show(childFragmentManager, modalBottomSheet.tag)

                childFragmentManager.setFragmentResultListener(
                    forgotBottomSheetRequestKey,
                    viewLifecycleOwner,
                    { _, bundle ->
                        val result = bundle.getString(forgotBottomSheetBundleKey)
                        result?.let {
                            viewModel.resetPasswordEmail(result)
                        }
                    })
            }
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            loadingFrame.visibility = View.VISIBLE
            loginProgressBar.visibility = View.VISIBLE
        } else {
            loginProgressBar.visibility = View.GONE
            loadingFrame.visibility = View.GONE
        }

    }

    private fun isEmailValid(email: String): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        return email.matches(emailPattern.toRegex())
    }

    private fun toHomeFragment() {
        findNavController().navigate(R.id.fromLoginToHomeFragment)
    }

    private fun forgotPasswordState() {
        viewModel.getResetPasswordEmailState().observe(viewLifecycleOwner, { response ->
            when (response) {
                is State.OnSuccess -> {
                    showLoading(false)
                    Toast.makeText(
                        activity,
                        "Cek email anda untuk membuat ulang password",
                        Toast.LENGTH_LONG
                    ).show()

                }

                is State.OnError -> {
                    showLoading(false)
                    response.message?.let {
                        Toast.makeText(activity, response.message, Toast.LENGTH_SHORT).show()
                    }
                }
                is State.OnLoading -> {
                    showLoading(true)
                }
            }

        })
    }

}