package com.example.explorelanka

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
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

        val jobs = listOf(
            Destination("Kandy", "Fulltime", "20k-30k", "Colombo", R.drawable.kandy1),
            Destination("Matara", "Part time", "18k-35k", "Colombo", R.drawable.matara1),
            Destination("Anuradhapura", "Part time", "8k-12k", "Galle", R.drawable.anuradapura3),
            Destination("Jaffna", "Fulltime", "10k-12k", "Matara", R.drawable.jaffna2),
            Destination("NuwaraEliya", "Fulltime", "8k-13k", "Kurunegala", R.drawable.nuwaraeliya2),
            Destination("Colombo", "Fulltime", "8k-13k", "Kurunegala", R.drawable.colombo)
        )

        adapter = DestinationAdapter(this, jobs)
        recyclerView.adapter = adapter

        // ✅ Filter on text change
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
