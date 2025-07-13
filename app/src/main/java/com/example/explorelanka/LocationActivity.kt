package com.example.explorelanka


import Attraction
import Location
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class LocationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Get destination name from intent
        val destinationName = intent.getStringExtra("destination_name")

        // Create location data based on destination
        val location = when (destinationName) {
            "Matara" -> Location(
                name = "Matara",
                headerImageResId = R.drawable.matara4,
                attractions = listOf(
                    Attraction("Paravi Dupatha", "Paravi Duwa Temple is a Buddhist temple...", R.drawable.matara1),
                    Attraction("Dondra Point", "Dondra Point is the southernmost part...", R.drawable.mataralighthouse),
                    Attraction("Mirissa", "Mirissa is a small fishing town...", R.drawable.mirissa),
                    Attraction("Hiriketiya Beach", "Hiriketiya Beach is a destination...", R.drawable.hiriketiya)
                )
            )
            "Kandy" -> Location(
                name = "Kandy",
                headerImageResId = R.drawable.kandy1,
                attractions = listOf(
                    Attraction("Temple of the Tooth", "Sacred Buddhist site", R.drawable.kandy1),
                    Attraction("Kandy Lake", "Beautiful lake in the city center", R.drawable.kandy1)
                )
            )
            else -> null
        }

        if (location == null) {
            Toast.makeText(this, "No location data found", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // Start LocationDetailsActivity with full Location
        val intent = Intent(this, LocationDetailsActivity::class.java)
        intent.putExtra("destination", location)
        startActivity(intent)
        finish() // Optional: close this activity
    }
}
