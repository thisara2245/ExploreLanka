package com.example.explorelanka

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.explorelanka.admin.AddDestinationActivity

class OnboardingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        val button = findViewById<Button>(R.id.buttonGetStarted)
        button.setOnClickListener {
            // Navigate to main screen
            val intent = Intent(this, OnboardingActivity2::class.java)
            startActivity(intent)
            finish()
        }
    }
}
