package com.example.explorelanka

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DestinationsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destinations)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewJobs)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Replace with your actual data and adapter
        val jobs = listOf(
            Destination("Kandy", "Fulltime", "20k-30k", "Colombo", R.drawable.kandy1),
            Destination("Matara", "Part time", "18k-35k", "Colombo", R.drawable.matara1),
            Destination("Anuradhapura", "Part time", "8k-12k", "Galle", R.drawable.anuradapura3),
            Destination("Jaffna", "Fulltime", "10k-12k", "Matara", R.drawable.jaffna2),
            Destination("NuwaraEliya", "Fulltime", "8k-13k", "Kurunegala", R.drawable.nuwaraeliya2),
            Destination("Colombo", "Fulltime", "8k-13k", "Kurunegala", R.drawable.colombo)
        )

        val adapter = DestinationAdapter(this, jobs)

        recyclerView.adapter = adapter
    }
}
