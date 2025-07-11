package com.example.explorelanka

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class HomeFragment : Fragment() {

    private lateinit var homeUserNameText: TextView
    private lateinit var databaseRef: DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Firebase setup
        auth = FirebaseAuth.getInstance()
        databaseRef = FirebaseDatabase.getInstance().getReference("users")
        homeUserNameText = view.findViewById(R.id.homeUserName)

        // Fetch and display name
        fetchUserName()

        // "View All" click
        view.findViewById<TextView>(R.id.viewall).setOnClickListener {
            startActivity(Intent(requireContext(), DestinationsActivity::class.java))
        }

        // Notifications
        view.findViewById<ImageView>(R.id.btnnotification).setOnClickListener {
            startActivity(Intent(requireContext(), NotificationsActivity::class.java))
        }

        // Kandy card click
        view.findViewById<CardView>(R.id.kandy).setOnClickListener {
            startActivity(Intent(requireContext(), ViewActivity::class.java))
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        fetchUserName() // Refresh on return from EditProfile
    }

    private fun fetchUserName() {
        val uid = auth.currentUser?.uid ?: return
        databaseRef.child(uid).child("name").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val name = snapshot.getValue(String::class.java)
                homeUserNameText.text = name ?: "User"
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), "Failed to load name", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
