package com.example.explorelanka

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.firebase.database.*

class TripPlannerFragment : Fragment() {

    private lateinit var spinnerStartLocation: Spinner
    private lateinit var spinnerTravelMode: Spinner
    private lateinit var membersEditText: EditText
    private lateinit var daysEditText: EditText
    private lateinit var budgetEditText: EditText
    private lateinit var findButton: Button
    private lateinit var databaseReference: DatabaseReference
    private var destinationList: MutableList<Destinations> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_trip_planner, container, false)

        // Link XML views
        spinnerStartLocation = view.findViewById(R.id.spinnerStartLocation)
        spinnerTravelMode = view.findViewById(R.id.spinnerTravelMode)
        membersEditText = view.findViewById(R.id.editTextMembers)
        daysEditText = view.findViewById(R.id.editTextDays)
        budgetEditText = view.findViewById(R.id.editTextBudget)
        findButton = view.findViewById(R.id.buttonFind)

        // Spinner data
        val locations = arrayOf("Colombo", "Kandy", "Jaffna", "Matara", "Anuradhapura", "NuwaraEliya", "Galle", "Polonnaruwa", "Matale", "Kurunagala", "Hambanthota")
        val modes = arrayOf("Car", "Bus", "Train", "Van")

        spinnerStartLocation.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, locations)
        spinnerTravelMode.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, modes)

        // Firebase reference
        databaseReference = FirebaseDatabase.getInstance().getReference("destinations")

        findButton.setOnClickListener {
            val start = spinnerStartLocation.selectedItem.toString()
            val mode = spinnerTravelMode.selectedItem.toString()
            val members = membersEditText.text.toString().toIntOrNull() ?: 0
            val days = daysEditText.text.toString().toIntOrNull() ?: 0
            val budget = budgetEditText.text.toString().toDoubleOrNull() ?: 0.0

            if (members <= 0 || days <= 0 || budget <= 0) {
                Toast.makeText(requireContext(), "Please enter valid input", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            fetchAndFilterDestinations(start, mode, members, days, budget)
        }

        return view
    }

    private fun fetchAndFilterDestinations(start: String, mode: String, members: Int, days: Int, budget: Double) {
        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                destinationList.clear()
                for (data in snapshot.children) {
                    val dest = data.getValue(Destinations::class.java)
                    dest?.let {
                        val travelCost = calculateTravelCost(start, it.name!!, mode, it.lat, it.lon)
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

    private fun calculateDistanceInKm(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val radius = 6371
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                Math.sin(dLon / 2) * Math.sin(dLon / 2)
        val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
        return radius * c
    }

    private fun getCoordinatesForLocation(location: String): Pair<Double, Double>? {
        return when (location) {
            "Colombo" -> Pair(6.9271, 79.8612)
            "Kandy" -> Pair(7.2906, 80.6337)
            "Jaffna" -> Pair(9.6615, 80.0255)
            "Matara" -> Pair(5.9485, 80.5353)
            "Anuradhapura" -> Pair(8.3114, 80.4037)
            "NuwaraEliya" -> Pair(6.9497, 80.7891)
            "Galle" -> Pair(6.0535, 80.2210)
            "Polonnaruwa" -> Pair(7.9403, 81.0188)
            "Matale" -> Pair(7.4675, 80.6234)
            "Kurunagala" -> Pair(7.4863, 80.3647)
            "Hambanthota" -> Pair(6.1240, 81.1185)
            else -> null
        }
    }

    private fun calculateTravelCost(start: String, end: String, mode: String, endLat: Double, endLon: Double): Double {
        val startCoords = getCoordinatesForLocation(start)
        if (startCoords == null) return 0.0

        val (startLat, startLon) = startCoords
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
}
