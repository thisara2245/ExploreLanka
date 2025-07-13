package com.example.explorelanka

import Location
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView



class LocationDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_details)

        Log.d("IntentDebug", "Intent has extras: ${intent.extras}")
        Log.d("IntentDebug", "Parcelable destination: ${intent.getParcelableExtra<Location>("destination")}")
        // ✅ Correct receiving
        val location = intent.getParcelableExtra<Location>("destination")


        if (location == null) {
            Toast.makeText(this, "Destination not found", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // Continue displaying the layout
        findViewById<TextView>(R.id.detailTitle).text = location.name
        findViewById<ImageView>(R.id.detailImage).setImageResource(location.headerImageResId)

        val recycler = findViewById<RecyclerView>(R.id.recyclerAttractions)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = AttractionAdapter(this, location.attractions)
    }
}
