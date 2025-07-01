package com.example.explorelanka


import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    private lateinit var backButton: ImageView
    private lateinit var editButton: ImageView
    private lateinit var profileImage: ImageView

    private lateinit var nameText: TextView
    private lateinit var emailText: TextView

    private lateinit var rewardPoints: TextView
    private lateinit var travelTrips: TextView
    private lateinit var bucketList: TextView

    private lateinit var profileOption: TextView
    private lateinit var bookmarkedOption: TextView
    private lateinit var previousTripsOption: TextView
    private lateinit var settingsOption: TextView
    private lateinit var versionOption: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // Initialize views
        backButton = findViewById(R.id.back_button)  // Update ID if added
        editButton = findViewById(R.id.edit_button)  // Update ID if added
        profileImage = findViewById(R.id.profile_image)

        nameText = findViewById(R.id.name_text)
        emailText = findViewById(R.id.email_text)

        rewardPoints = findViewById(R.id.reward_points)
        travelTrips = findViewById(R.id.travel_trips)
        bucketList = findViewById(R.id.bucket_list)

        profileOption = findViewById(R.id.option_profile)
        bookmarkedOption = findViewById(R.id.option_bookmarked)
        previousTripsOption = findViewById(R.id.option_previous)
        settingsOption = findViewById(R.id.option_settings)
        versionOption = findViewById(R.id.option_version)

        // Set data
        nameText.text = "Leonardo"
        emailText.text = "leonardo@gmail.com"
        rewardPoints.text = "360"
        travelTrips.text = "238"
        bucketList.text = "473"

        // Click listeners
        backButton.setOnClickListener {
            finish()
        }

        editButton.setOnClickListener {
            Toast.makeText(this, "Edit Profile Clicked", Toast.LENGTH_SHORT).show()
        }

        profileOption.setOnClickListener {
            Toast.makeText(this, "Profile Clicked", Toast.LENGTH_SHORT).show()
        }

        bookmarkedOption.setOnClickListener {
            Toast.makeText(this, "Bookmarked Clicked", Toast.LENGTH_SHORT).show()
        }

        previousTripsOption.setOnClickListener {
            Toast.makeText(this, "Previous Trips Clicked", Toast.LENGTH_SHORT).show()
        }

        settingsOption.setOnClickListener {
            Toast.makeText(this, "Settings Clicked", Toast.LENGTH_SHORT).show()
        }

        versionOption.setOnClickListener {
            Toast.makeText(this, "Version Clicked", Toast.LENGTH_SHORT).show()
        }
    }
}
