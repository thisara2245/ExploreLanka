package com.example.explorelanka

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.firebase.database.*
import android.app.Activity

class TripPlannerFragment : Fragment() {

    private lateinit var textViewSelectedLocation: TextView
    private lateinit var buttonPickOnMap: Button
    private lateinit var spinnerTravelMode: Spinner
    private lateinit var membersEditText: EditText
    private lateinit var daysEditText: EditText
    private lateinit var budgetEditText: EditText
    private lateinit var findButton: Button
    private lateinit var databaseReference: DatabaseReference
    private var destinationList: MutableList<Destinations> = mutableListOf()
    private var selectedLat: Double? = null
    private var selectedLon: Double? = null

    private val MAP_PICKER_REQUEST = 1001

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_trip_planner, container, false)

        textViewSelectedLocation = view.findViewById(R.id.textViewSelectedLocation)
        buttonPickOnMap = view.findViewById(R.id.buttonPickOnMap)
        spinnerTravelMode = view.findViewById(R.id.spinnerTravelMode)
        membersEditText = view.findViewById(R.id.editTextMembers)
        daysEditText = view.findViewById(R.id.editTextDays)
        budgetEditText = view.findViewById(R.id.editTextBudget)
        findButton = view.findViewById(R.id.buttonFind)
        databaseReference = FirebaseDatabase.getInstance().getReference("destinations")

        // Setup spinner adapter (if not already)
        val modes = arrayOf("Car", "Bus", "Train", "Van")
        spinnerTravelMode.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, modes)

        buttonPickOnMap.setOnClickListener {
            val intent = Intent(requireContext(), MapPickerActivity::class.java)
            startActivityForResult(intent, MAP_PICKER_REQUEST)
        }
        textViewSelectedLocation.setOnClickListener { buttonPickOnMap.performClick() }

        findButton.setOnClickListener {
            val mode = spinnerTravelMode.selectedItem.toString()
            val members = membersEditText.text.toString().toIntOrNull() ?: 0
            val days = daysEditText.text.toString().toIntOrNull() ?: 0
            val budget = budgetEditText.text.toString().toDoubleOrNull() ?: 0.0

            if (selectedLat == null || selectedLon == null) {
                Toast.makeText(requireContext(), "Please select a start location on the map", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (members <= 0 || days <= 0 || budget <= 0) {
                Toast.makeText(requireContext(), "Please enter valid input", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            fetchAndFilterDestinations(selectedLat!!, selectedLon!!, mode, members, days, budget)
        }

        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == MAP_PICKER_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            selectedLat = data.getDoubleExtra("latitude", 0.0)
            selectedLon = data.getDoubleExtra("longitude", 0.0)
            textViewSelectedLocation.text = "Lat: ${"%.4f".format(selectedLat)}, Lon: ${"%.4f".format(selectedLon)}"
        }
    }

    private fun fetchAndFilterDestinations(
        startLat: Double, startLon: Double,
        mode: String, members: Int, days: Int, budget: Double
    ) {
        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                destinationList.clear()
                for (data in snapshot.children) {
                    val dest = data.getValue(Destinations::class.java)
                    dest?.let {
                        val travelCost = calculateTravelCost(startLat, startLon, it.lat, it.lon, mode)
                        val stayCost = (it.costofonedayPerPerson) * members * days
                        val totalCost = stayCost + travelCost

                        if (totalCost <= budget) {
                            destinationList.add(it)
                        }
                    }
                }

                if (destinationList.isEmpty()) {
                    Toast.makeText(requireContext(), "No destinations found within your budget", Toast.LENGTH_LONG).show()
                } else {
                    val intent = Intent(requireContext(), ResultActivity::class.java)
                    intent.putParcelableArrayListExtra("destinations", ArrayList(destinationList))
                    startActivity(intent)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), "Database Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun calculateTravelCost(
        startLat: Double, startLon: Double,
        endLat: Double, endLon: Double,
        mode: String
    ): Double {
        val distanceInKm = calculateDistanceInKm(startLat, startLon, endLat, endLon)
        val costPerKm = when (mode) {
            "Van" -> 90.0
            "Car" -> 60.0
            "Bus" -> 120.0
            "Train" -> 20.0
            else -> 25.0
        }
        return distanceInKm * costPerKm
    }

    private fun calculateDistanceInKm(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val radius = 6371 // Earth radius in km
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                Math.sin(dLon / 2) * Math.sin(dLon / 2)
        val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
        return radius * c
    }
}