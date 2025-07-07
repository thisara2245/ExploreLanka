package com.example.explorelanka

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // "View All" Text Click
        val viewAllTextView = view.findViewById<TextView>(R.id.viewall)
        viewAllTextView.setOnClickListener {
            val intent = Intent(requireContext(), DestinationsActivity::class.java)
            startActivity(intent)
        }

        // Kandy Card Click
        val kandyCard = view.findViewById<CardView>(R.id.kandy)
        kandyCard.setOnClickListener {
            val intent = Intent(requireContext(), ViewActivity::class.java)
            startActivity(intent)
        }

        return view
    }
}
