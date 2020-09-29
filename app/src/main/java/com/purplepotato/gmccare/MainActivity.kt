package com.purplepotato.gmccare

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = Navigation.findNavController(this, R.id.navHostFragment)
        NavigationUI.setupWithNavController(navigation_view, navController)
        navigation_view.setNavigationItemSelectedListener(this)
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
        when (item.itemId) {
            R.id.changePassword -> {
                Toast.makeText(this, "change password", Toast.LENGTH_SHORT).show()
                return true
            }

            R.id.signOut -> {
                Toast.makeText(this, "sign out", Toast.LENGTH_SHORT).show()
                Snackbar.make(drawer_layout, "sign out", Snackbar.LENGTH_LONG)
                return true
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}