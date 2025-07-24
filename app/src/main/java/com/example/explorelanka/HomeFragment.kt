package com.example.explorelanka

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class HomeFragment : Fragment() {

    private lateinit var homeUserNameText: TextView
    private lateinit var databaseRef: DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        databaseRef = FirebaseDatabase.getInstance().getReference("users")
    }

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        homeUserNameText = view.findViewById(R.id.homeUserName)

        fetchUserName()

        view.findViewById<TextView>(R.id.viewall).setOnClickListener {
            startActivity(Intent(requireContext(), DestinationsActivity::class.java))
        }

        view.findViewById<ImageView>(R.id.btnnotification).setOnClickListener {
            startActivity(Intent(requireContext(), NotificationsActivity::class.java))
        }

        // Set CardView click listeners for each destination
        view.findViewById<CardView>(R.id.cardkandy).setOnClickListener {
            openLocation("Kandy")
        }
        view.findViewById<CardView>(R.id.cardNuwaraEliya).setOnClickListener {
            openLocation("NuwaraEliya")
        }
        view.findViewById<CardView>(R.id.cardAnuradhapura).setOnClickListener {
            openLocation("Anuradhapura")
        }
        view.findViewById<CardView>(R.id.cardColombo).setOnClickListener {
            openLocation("Colombo")
        }
        view.findViewById<CardView>(R.id.cardJaffna).setOnClickListener {
            openLocation("Jaffna")
        }

        return view
    }

    private fun openLocation(destinationName: String) {
        val intent = Intent(requireContext(), LocationActivity::class.java)
        intent.putExtra("destination_name", destinationName)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        fetchUserName()
    }

    private fun fetchUserName() {
        val uid = auth.currentUser?.uid
        if (uid == null) {
            Toast.makeText(requireContext(), "User not logged in", Toast.LENGTH_SHORT).show()
            return
        }

        databaseRef.child(uid).child("name")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val name = snapshot.getValue(String::class.java)
                    homeUserNameText.text = name ?: "User"
                    Log.d("HomeFragment", "Fetched name: $name")
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(requireContext(), "Failed to load name", Toast.LENGTH_SHORT).show()
                    Log.e("HomeFragment", "Firebase error: ${error.message}")
                }
            })
    }
}