package com.example.explorelanka

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ProfileFragment : Fragment() {

    private lateinit var imageProfile: ImageView
    private lateinit var textName: TextView
    private lateinit var databaseRef: DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        // Views
        imageProfile = view.findViewById(R.id.imageProfile)
        textName = view.findViewById(R.id.textName)

        // Firebase
        auth = FirebaseAuth.getInstance()
        databaseRef = FirebaseDatabase.getInstance().getReference("users")

        // Load user profile info
        fetchUserProfile()

        // Edit Profile
        view.findViewById<View>(R.id.optionEditName)?.setOnClickListener {
            val intent = Intent(requireContext(), EditProfileActivity::class.java)
            intent.putExtra("currentName", textName.text.toString())
            startActivity(intent)
        }

        // Change Password
        view.findViewById<View>(R.id.optionChangePassword)?.setOnClickListener {
            val intent = Intent(requireContext(), ForgotPasswordActivity::class.java)
            startActivity(intent)
        }

        // Logout
        view.findViewById<View>(R.id.optionLogout)?.setOnClickListener {
            auth.signOut()
            Toast.makeText(requireContext(), "Logged out", Toast.LENGTH_SHORT).show()
            val intent = Intent(requireContext(), LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

        // Other clicks
        setOptionClick(view, R.id.optionSettings, "Settings clicked")
        setOptionClick(view, R.id.optionPreferences, "Preferences clicked")

        return view
    }

    override fun onResume() {
        super.onResume()
        fetchUserProfile() // Refresh after edit
    }

    private fun fetchUserProfile() {
        val currentUser = auth.currentUser
        currentUser?.uid?.let { userId ->
            databaseRef.child(userId).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val name = snapshot.child("name").getValue(String::class.java)
                    val imageUri = snapshot.child("imageUri").getValue(String::class.java)

                    textName.text = name ?: "User"

                    // Load image if URI is available
                    if (!imageUri.isNullOrEmpty()) {
                        imageProfile.setImageURI(Uri.parse(imageUri))
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(requireContext(), "Failed to load profile", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    private fun setOptionClick(root: View, viewId: Int, message: String) {
        val view = root.findViewById<View>(viewId)
        view?.setOnClickListener {
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
    }
}
