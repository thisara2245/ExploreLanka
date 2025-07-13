package com.example.explorelanka


import Destination
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DestinationsActivity : AppCompatActivity() {

    private lateinit var backButton: ImageButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchBar: EditText
    private lateinit var adapter: DestinationAdapter // Make adapter global

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destinations)

        backButton = findViewById(R.id.btnBack1)
        recyclerView = findViewById(R.id.recyclerViewJobs)
        searchBar = findViewById(R.id.searchBar)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val destinations = listOf(
            Destination("1", "Kandy", "Fulltime", "20k-30k", "Colombo", R.drawable.kandy1),
            Destination("2", "Matara", "Part time", "18k-35k", "Colombo", R.drawable.matara1),
            Destination(
                "3",
                "Anuradhapura",
                "Part time",
                "8k-12k",
                "Galle",
                R.drawable.anuradapura3
            ),
            Destination("4", "Jaffna", "Fulltime", "10k-12k", "Matara", R.drawable.jaffna2),
            Destination(
                "5",
                "NuwaraEliya",
                "Fulltime",
                "8k-13k",
                "Kurunegala",
                R.drawable.nuwaraeliya2
            ),
            Destination("6", "Colombo", "Fulltime", "8k-13k", "Kurunegala", R.drawable.colombo)
        )

        // ✅ Initialize adapter first
        val adapter = DestinationAdapter(this, destinations) { selectedDestination ->

            // Open LocationActivity and pass only the destination name
            val intent = Intent(this, LocationActivity::class.java)
            intent.putExtra("destination_name", selectedDestination.title) // e.g., "Matara"
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        // ✅ Then add the filter (after adapter is not null)
        searchBar.addTextChangedListener(object : android.text.TextWatcher {
            override fun afterTextChanged(s: android.text.Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.filter(s.toString())
            }
        })

        // Back Button
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("navigate_to", "home")
            startActivity(intent)
            finish()
        }
    }
}