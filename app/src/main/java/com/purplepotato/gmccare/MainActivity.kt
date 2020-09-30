package com.purplepotato.gmccare

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.change_password_bottom_sheet.view.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var navController: NavController
    private lateinit var bottomSheetDialog: BottomSheetDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = Navigation.findNavController(this, R.id.navHostFragment)
        NavigationUI.setupWithNavController(navigation_view, navController)
        navigation_view.setNavigationItemSelectedListener(this)

        bottomSheetDialog = BottomSheetDialog(this)
        val bottomSheetView = layoutInflater.inflate(
            R.layout.change_password_bottom_sheet, null
        )

        bottomSheetDialog.setContentView(bottomSheetView)

        bottomSheetView.btnChangePassword.setOnClickListener {
            Toast.makeText(this, "reset password", Toast.LENGTH_SHORT).show()
        }
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
//        val fbAuth = FirebaseAuth.getInstance()
//        val currentUser = fbAuth.currentUser
//
//        if (currentUser == null) {
//            Toast.makeText(this, "Anda belum masuk ke akun", Toast.LENGTH_SHORT).show()
//            return false
//        }

        when (item.itemId) {
            R.id.changePassword -> {
                bottomSheetDialog.show()
            }

            R.id.signOut -> {
                /*to-do : sign out firebase account
                    your code
                 */
                if (navController.currentDestination!!.id == R.id.queueFragment) {
                    navController.navigate(R.id.fromQueueToLoginFragment)
                }

                if (navController.currentDestination!!.id == R.id.homeFragment) {
                    navController.navigate(R.id.fromHomeToLoginFragment)
                }
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

    fun showForgotPasswordBottomSheet() {

    }

}