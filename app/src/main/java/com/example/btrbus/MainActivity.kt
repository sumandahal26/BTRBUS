package com.example.btrbus

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.android.material.textfield.MaterialAutoCompleteTextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // ---------------- DRAWER ----------------

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawlayout)
        val navView = findViewById<NavigationView>(R.id.navView)
        val menuBtn = findViewById<ImageButton>(R.id.menuBtn)

        // open drawer on button click
        menuBtn.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        // navigation item clicks
        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> toast("Home clicked")
                R.id.nav_mess -> toast("Messages clicked")
                R.id.nav_sync -> toast("Sync clicked")
                R.id.nav_trash -> toast("Trash clicked")
                R.id.nav_settings -> toast("Settings clicked")
                R.id.nav_login -> toast("Login clicked")
                R.id.nav_share -> toast("Share clicked")
                R.id.nav_rate -> toast("Rate clicked")
            }

            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        // ---------------- LANGUAGE DROPDOWN ----------------

        val languageDropdown =
            findViewById<MaterialAutoCompleteTextView>(R.id.languageDropdown)

        val languages = arrayOf("English", "Assamese")

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            languages
        )

        languageDropdown.setAdapter(adapter)

        languageDropdown.setOnItemClickListener { _, _, position, _ ->
            toast("Selected: ${languages[position]}")
        }

        // ---------------- EDGE TO EDGE ----------------

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom)
            insets
        }
    }

    // helper toast function
    private fun toast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    // close drawer on back press
    override fun onBackPressed() {
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawlayout)

        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START)
        else
            super.onBackPressed()
    }
}
