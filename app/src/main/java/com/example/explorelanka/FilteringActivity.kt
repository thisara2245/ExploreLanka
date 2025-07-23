package com.example.explorelanka

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*

class FilteringActivity : Fragment() {

    private lateinit var locationSpinner: Spinner
    private lateinit var mapButton: Button
    private lateinit var dateText: TextView
    private lateinit var membersPicker: NumberPicker
    private lateinit var budgetInput: EditText
    private lateinit var modeSpinner: Spinner
    private lateinit var findButton: Button

    private var selectedDate: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_trip_planner, container, false)

        // Initialize views
        locationSpinner = view.findViewById(R.id.spinnerLocation)
        mapButton = view.findViewById(R.id.btnSelectFromMap)
        dateText = view.findViewById(R.id.txtSelectedDate)
        membersPicker = view.findViewById(R.id.numberPickerMembers)
        budgetInput = view.findViewById(R.id.etBudget)
        modeSpinner = view.findViewById(R.id.spinnerMode)
        findButton = view.findViewById(R.id.btnFindDestinations)

        setupLocationSpinner()
        setupModeSpinner()
        setupNumberPicker()
        setupDatePicker()
        setupMapButton()
        setupFindButton()

        return view
    }

    private fun setupLocationSpinner() {
        val locations = listOf("Colombo", "Kandy", "Galle", "Jaffna", "Anuradhapura")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, locations)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        locationSpinner.adapter = adapter
    }

    private fun setupModeSpinner() {
        val modes = listOf("Car", "Bus", "Train", "Plane", "Bike")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, modes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        modeSpinner.adapter = adapter
    }

    private fun setupNumberPicker() {
        membersPicker.minValue = 1
        membersPicker.maxValue = 20
        membersPicker.wrapSelectorWheel = true
    }

    private fun setupDatePicker() {
        val calendar = Calendar.getInstance()
        dateText.setOnClickListener {
            val datePicker = DatePickerDialog(
                requireContext(),
                { _, year, month, day ->
                    calendar.set(year, month, day)
                    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US)
                    selectedDate = sdf.format(calendar.time)
                    dateText.text = selectedDate
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePicker.datePicker.minDate = System.currentTimeMillis()
            datePicker.show()
        }
    }

    private fun setupMapButton() {
        mapButton.setOnClickListener {
            // This should launch a Google Maps activity for picking a location
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = android.net.Uri.parse("geo:0,0?q=Select+Location")
            intent.setPackage("com.google.android.apps.maps")
            if (intent.resolveActivity(requireActivity().packageManager) != null) {
                startActivity(intent)
            } else {
                Toast.makeText(requireContext(), "Google Maps is not installed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupFindButton() {
        findButton.setOnClickListener {
            val location = locationSpinner.selectedItem.toString()
            val members = membersPicker.value
            val budget = budgetInput.text.toString()
            val mode = modeSpinner.selectedItem.toString()

            if (selectedDate.isEmpty() || budget.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Here you would send the values to the destination filter logic or next screen
            Toast.makeText(
                requireContext(),
                "Searching from $location on $selectedDate for $members people using $mode within LKR $budget",
                Toast.LENGTH_LONG
            ).show()

            // TODO: Navigate to destination list with this data
        }
    }
}