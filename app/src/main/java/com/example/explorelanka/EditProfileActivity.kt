package com.example.explorelanka

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class EditProfileActivity : AppCompatActivity() {

    private lateinit var profileImage: ImageView
    private lateinit var editName: EditText
    private lateinit var btnSave: Button

    private val REQUEST_IMAGE_PICK = 100
    private var selectedImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_profile)

        profileImage = findViewById(R.id.imageEditProfile)
        editName = findViewById(R.id.editProfileName)
        btnSave = findViewById(R.id.btnSaveProfile)

        editName.setText(intent.getStringExtra("currentName"))

        profileImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, REQUEST_IMAGE_PICK)
        }

        btnSave.setOnClickListener {
            val name = editName.text.toString().trim()
            if (name.isEmpty()) {
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val uid = FirebaseAuth.getInstance().currentUser?.uid
            if (uid != null) {
                val dbRef = FirebaseDatabase.getInstance().getReference("users").child(uid)
                val updates = mutableMapOf<String, Any>("name" to name)
                selectedImageUri?.let { updates["imageUri"] = it.toString() }

                dbRef.updateChildren(updates)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Profile Updated!", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Failed to update profile", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_PICK && resultCode == Activity.RESULT_OK) {
            selectedImageUri = data?.data
            profileImage.setImageURI(selectedImageUri)
        }
    }
}
