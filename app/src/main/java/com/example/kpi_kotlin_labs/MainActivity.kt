package com.example.kpi_kotlin_labs

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.kpi_kotlin_labs.Calculator1Fragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        // Default fragment (Calculator 1)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, Calculator1Fragment())
            .commit()

        // Handle navigation drawer clicks
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_calculator1 -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, Calculator1Fragment())
                        .commit()
                }
                R.id.nav_calculator2 -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, Calculator2Fragment())
                        .commit()
                }
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
