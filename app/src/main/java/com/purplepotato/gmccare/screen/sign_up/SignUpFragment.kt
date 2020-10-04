package com.purplepotato.gmccare.screen.sign_up

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.purplepotato.gmccare.R
import com.purplepotato.gmccare.State
import com.purplepotato.gmccare.service.FirebaseAuthentication
import com.purplepotato.gmccare.service.RealtimeDatabase
import kotlinx.android.synthetic.main.fragment_sign_up.*
import java.text.SimpleDateFormat
import java.util.*

class SignUpFragment : Fragment(), View.OnClickListener {

    private lateinit var viewModel: SignUpViewModel
    private lateinit var calendar: Calendar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fbAuthentication = FirebaseAuthentication()
        val fbDatabase = RealtimeDatabase()
        val viewModelFactory = SignUpViewModelFactory(fbAuthentication, fbDatabase)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(SignUpViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnSignUp.setOnClickListener(this)
        btnBackToLoginFragment.setOnClickListener(this)
        tvBackToLoginFragment.setOnClickListener(this)
        et_birth_date.setOnClickListener(this)
        btnSetBirthDate.setOnClickListener(this)
        calendar = Calendar.getInstance()
        getSignUpState()


    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnSignUp -> signUp()
            R.id.btnBackToLoginFragment -> requireActivity().onBackPressed()
            R.id.tvBackToLoginFragment -> requireActivity().onBackPressed()
            R.id.btnSetBirthDate -> {
                val dataSetListener =
                    DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                        calendar.set(Calendar.YEAR, year)
                        calendar.set(Calendar.MONTH,monthOfYear)
                        calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)
                        updateDateInView()
                    }

                DatePickerDialog(requireContext(),dataSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show()
            }
            R.id.et_birth_date->{
                val dataSetListener =
                    DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                        calendar.set(Calendar.YEAR, year)
                        calendar.set(Calendar.MONTH,monthOfYear)
                        calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)
                        updateDateInView()
                    }

                DatePickerDialog(requireContext(),dataSetListener,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)).show()
            }
        }
    }

    private fun signUp() {
        val name = et_name.text.toString().trim()
        val birthDate = et_birth_date.text.toString().trim()
        val nik = et_nik.text.toString().trim()
        val email = et_email.text.toString().trim()
        val password = et_password.text.toString().trim()

        if (name.isEmpty()) {
            et_name.error = "Field ini tidak boleh kosong"
            return
        }

        if (birthDate.isEmpty()) {
            et_birth_date.error = "Field ini tidak boleh kosong"
            return
        }

        if (nik.isEmpty()) {
            et_nik.error = "Field ini tidak boleh kosong"
            return
        }

        if (email.isEmpty()) {
            et_email.error = "Field ini tidak boleh kosong"
            return
        }

        if (password.isEmpty()) {
            et_email.error = "Field ini tidak boleh kosong"
            return
        }

        if (password.length < 8) {
            et_password.error = "Password kurang dari 8 karakter"
            return
        }
        val dateToLong = convertDateToLong(birthDate)

        viewModel.createUserWithEmailAndPassword(email, password, name, dateToLong, nik)
    }

    private fun getSignUpState() {
        viewModel.getSignUpState().observe(viewLifecycleOwner, { response ->
            when (response) {
                is State.OnSuccess -> {
                    showLoading(false)
                    findNavController().navigate(R.id.toVerificationFragment)
                }
                is State.OnLoading -> {
                    showLoading(true)
                }
                is State.OnError -> {
                    showLoading(false)
                    response.message?.let {
                        Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            signUpLoadingFrame.visibility = View.VISIBLE
            signUpProgressBar.visibility = View.VISIBLE
        } else {
            signUpProgressBar.visibility = View.GONE
            signUpLoadingFrame.visibility = View.GONE
        }

    }

    private fun updateDateInView(){
        val formatDate = "d MMMM YYYY"
        val sdf = SimpleDateFormat(formatDate, Locale.getDefault())
        val date = calendar.time
        et_birth_date.setText(sdf.format(date))

    }

    private fun convertDateToLong(date: String): Long {
        val formatDate = "d MMMM YYYY"
        val sdf = SimpleDateFormat(formatDate, Locale.getDefault())

        return sdf.parse(date).time
    }
}