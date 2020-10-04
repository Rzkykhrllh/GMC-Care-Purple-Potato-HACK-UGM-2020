package com.purplepotato.gmccare.screen.main

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.purplepotato.gmccare.R
import com.purplepotato.gmccare.State
import com.purplepotato.gmccare.pref.Preferences
import com.purplepotato.gmccare.service.FirebaseAuthentication
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.change_password_bottom_sheet.view.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var navController: NavController
    private lateinit var bottomSheetDialog: BottomSheetDialog
    private lateinit var viewModel: MainViewModel
    private lateinit var sharedPreferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fbAuthentication = FirebaseAuthentication()
        val viewModelFactory = MainViewModelFactory(fbAuthentication)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        navController = Navigation.findNavController(this, R.id.navHostFragment)
        NavigationUI.setupWithNavController(navigation_view, navController)
        navigation_view.setNavigationItemSelectedListener(this)

        bottomSheetDialog = BottomSheetDialog(this)
        val bottomSheetView = layoutInflater.inflate(
            R.layout.change_password_bottom_sheet, null
        )

        bottomSheetDialog.setContentView(bottomSheetView)

        bottomSheetView.btnChangePassword.setOnClickListener {
            val email = bottomSheetView.et_email_password_recovery.text.toString().trim()

            if (email.isNotEmpty()) {
                viewModel.resetPasswordEmail(email)
            } else {
                bottomSheetView.et_email_password_recovery.error = "Field ini tidak boleh kosong"
            }

            bottomSheetView.et_email_password_recovery.setText("")
            bottomSheetDialog.hide()
        }

        forgotPasswordState()

        sharedPreferences = Preferences(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp() || NavigationUI.navigateUp(
            navController,
            drawer_layout
        )
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val fbAuth = FirebaseAuth.getInstance()
        val currentUser = fbAuth.currentUser

        if (currentUser == null) {
            Toast.makeText(this, "Anda belum masuk ke akun", Toast.LENGTH_SHORT).show()
            return false
        }

        when (item.itemId) {
            R.id.changePassword -> {
                bottomSheetDialog.show()
            }

            R.id.signOut -> {
                showSignOutAlertDialog()
            }

            R.id.homeFragment -> {
                if (navController.currentDestination!!.id == R.id.loginFragment) {
                    navController.navigate(R.id.fromLoginToHomeFragment)
                }

                if (navController.currentDestination!!.id == R.id.queueFragment) {
                    navController.navigate(R.id.fromQueueToHomeFragment)
                }
            }

            R.id.queueFragment -> {
                if (navController.currentDestination!!.id == R.id.homeFragment) {
                    navController.navigate(R.id.toQueueFragment)
                }

            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun showSignOutAlertDialog() {

        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setMessage("Apa Anda yakin ingin keluar?")
        alertDialog.setPositiveButton("Keluar") { _, _ ->
            sharedPreferences.setIsQueued(false)
            sharedPreferences.setUserQueueNumber(-1)
            viewModel.signOut()

            if (navController.currentDestination!!.id == R.id.queueFragment) {
                navController.navigate(R.id.fromQueueToLoginFragment)
            }

            if (navController.currentDestination!!.id == R.id.homeFragment) {
                navController.navigate(R.id.fromHomeToLoginFragment)
            }
        }.setNegativeButton("Batal") { _, _ -> }

        alertDialog.show()
    }

    private fun forgotPasswordState() {
        viewModel.getResetPasswordEmailState().observe(this, { response ->
            when (response) {
                is State.OnSuccess -> {
                    Toast.makeText(
                        this,
                        "Cek email anda untuk mengganti password",
                        Toast.LENGTH_LONG
                    ).show()
                }

                is State.OnError -> {
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                }

                is State.OnLoading -> {
                }
            }

        })
    }

}