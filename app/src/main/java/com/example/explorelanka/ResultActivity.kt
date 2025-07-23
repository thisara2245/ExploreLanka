package com.example.explorelanka

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ResultActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DestinationsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val destinations = intent.getParcelableArrayListExtra<Destinations>("destinations")

        if (destinations != null && destinations.isNotEmpty()) {
            adapter = DestinationsAdapter(destinations) { selectedDestination ->
                val intent = Intent(this, LocationDetailsActivity::class.java)
                intent.putExtra("title", selectedDestination.name)
                intent.putExtra("imgUrl", selectedDestination.imgUrl)
                intent.putExtra("description", selectedDestination.description)
                startActivity(intent)
            }
            recyclerView.adapter = adapter
        } else {
            Toast.makeText(this, "No destinations to show", Toast.LENGTH_SHORT).show()
        }
    }
}
