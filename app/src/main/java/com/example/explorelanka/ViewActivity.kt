package com.example.explorelanka

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view) // Make sure this file exists in res/layout
    }
}
