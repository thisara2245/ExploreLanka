package com.example.explorelanka


import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase

class AddDestinationActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etLatitude: EditText
    private lateinit var etLongitude: EditText
    private lateinit var etcostofonedayPerPerson: EditText
    private lateinit var etDescription: EditText
    private lateinit var etimgUrl: EditText
    private lateinit var btnSubmitDestination: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.destiupdate)

        // Initialize views
        etName = findViewById(R.id.etName)
        etLatitude = findViewById(R.id.etLatitude)
        etLongitude = findViewById(R.id.etLongitude)
        etcostofonedayPerPerson = findViewById(R.id.etcostofonedayPerPerson)
        etDescription = findViewById(R.id.etDescription)
        etimgUrl = findViewById(R.id.etimgUrl)
        btnSubmitDestination = findViewById(R.id.btnSubmitDestination)

        // Firebase database reference
        val database = FirebaseDatabase.getInstance().getReference("destinations")

        btnSubmitDestination.setOnClickListener {
            val name = etName.text.toString().trim()
            val lat = etLatitude.text.toString().toDoubleOrNull()
            val lon = etLongitude.text.toString().toDoubleOrNull()
            val costofonedayPerPerson = etcostofonedayPerPerson.text.toString().toIntOrNull()
            val imgUrl = etimgUrl.text.toString().trim()
            val description = etDescription.text.toString().trim()

            // Validate inputs
            if (name.isEmpty() || lat == null || lon == null || costofonedayPerPerson == null || imgUrl.isEmpty() ||  description.isEmpty()) {
                Toast.makeText(this, "Please fill all fields correctly", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Create a destination map
            val destination = mapOf(
                "name" to name,
                "lat" to lat,
                "lon" to lon,
                "costofonedayPerPerson" to costofonedayPerPerson,
                "imgUrl" to imgUrl,
                "description" to description
            )

            // Save to Firebase
            database.push().setValue(destination)
                .addOnSuccessListener {
                    Toast.makeText(this, "Destination added successfully", Toast.LENGTH_SHORT).show()
                    clearFields()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Failed to add destination", Toast.LENGTH_SHORT).show()
                }
        }
    }

    private fun clearFields() {
        etName.text.clear()
        etLatitude.text.clear()
        etLongitude.text.clear()
        etcostofonedayPerPerson.text.clear()
        etimgUrl.text.clear()
        etDescription.text.clear()
    }
}
