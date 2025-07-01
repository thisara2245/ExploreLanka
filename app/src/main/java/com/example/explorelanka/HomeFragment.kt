package com.example.explorelanka

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Find the TextView by ID
        val viewAllTextView = view.findViewById<TextView>(R.id.viewall)

        // Set click listener
        viewAllTextView.setOnClickListener {

            Toast.makeText(requireContext(), "View all clicked", Toast.LENGTH_SHORT).show()


        }

        return view
    }
}

