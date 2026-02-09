package com.example.btrbus

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.MaterialAutoCompleteTextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Reference to dropdown
        val languageDropdown =
            findViewById<MaterialAutoCompleteTextView>(R.id.languageDropdown)

        // Dropdown values
        val languages = arrayOf("English", "Assamese")

        // Adapter
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            languages
        )

        languageDropdown.setAdapter(adapter)

        // Item click listener
        languageDropdown.setOnItemClickListener { _, _, position, _ ->
            val selectedLanguage = languages[position]

            Toast.makeText(
                this,
                "Selected: $selectedLanguage",
                Toast.LENGTH_SHORT
            ).show()
            // Later: change app language here
        }

        // Edge-to-edge padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
