package com.example.explorelanka

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class ViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view) // Make sure this file exists in res/layout


        // Back button setup
        val backButton: ImageButton = findViewById(R.id.btnBack1)
        backButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()

        }

        //See on the map Button
        val btnMap = findViewById<Button>(R.id.mapButton)
        btnMap.setOnClickListener {
            val intent = Intent(this, ActivityGMap::class.java)
            startActivity(intent)
        }

    }
}
