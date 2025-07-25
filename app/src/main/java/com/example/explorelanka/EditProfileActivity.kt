package com.example.explorelanka

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.storage.storage
import kotlinx.coroutines.launch
import java.util.*

class EditProfileActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var uploadButton: Button
    private lateinit var nameEditText: EditText
    private val PICK_IMAGE_REQUEST = 1
    private var selectedImageUri: Uri? = null

    private val supabase: SupabaseClient by lazy {
        createSupabaseClient(
            supabaseUrl = "https://ocxlytkzvfkirtvcimat.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im9jeGx5dGt6dmZraXJ0dmNpbWF0Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NTMzODg3MzYsImV4cCI6MjA2ODk2NDczNn0.JRTs5NRCzWev5TUNM4etT0vZOcSdaYlmtWBtKrG8p-A"
        ) {
            install(io.github.jan.supabase.storage.Storage)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_profile)

        imageView = findViewById(R.id.imageEditProfile)
        uploadButton = findViewById(R.id.btnSaveProfile)
        nameEditText = findViewById(R.id.editProfileName)

        imageView.setOnClickListener {
            pickImageFromGallery()
        }

        uploadButton.setOnClickListener {
            val name = nameEditText.text.toString().trim()

            if (name.isEmpty()) {
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            selectedImageUri?.let {
                uploadImageToSupabase(it, name)
            } ?: run {
                updateNameOnly(name)
            }
        }
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            selectedImageUri = data?.data
            imageView.setImageURI(selectedImageUri)
        }
    }

    private fun uploadImageToSupabase(imageUri: Uri, name: String) {
        lifecycleScope.launch {
            try {
                val inputStream = contentResolver.openInputStream(imageUri)
                val bytes = inputStream!!.readBytes()
                inputStream.close()

                val fileName = "profile_${UUID.randomUUID()}.jpg"

                // Upload image to Supabase storage
                supabase.storage.from("profile-pictures")
                    .upload(path = fileName, data = bytes, upsert = true)

                // Get the public URL
                val publicUrl = supabase.storage.from("profile-pictures").publicUrl(fileName)

                // Save name and image URL to Firebase
                val userId = FirebaseAuth.getInstance().currentUser?.uid
                val databaseRef = FirebaseDatabase.getInstance().getReference("users").child(userId!!)
                val updates = mapOf(
                    "name" to name,
                    "profileImageUrl" to publicUrl
                )
                databaseRef.updateChildren(updates)

                Toast.makeText(this@EditProfileActivity, "Profile updated successfully", Toast.LENGTH_SHORT).show()
                finish()

            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this@EditProfileActivity, "Upload failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateNameOnly(name: String) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        val databaseRef = FirebaseDatabase.getInstance().getReference("Users").child(userId!!)
        databaseRef.child("name").setValue(name)
        Toast.makeText(this, "Name updated successfully", Toast.LENGTH_SHORT).show()
        finish()
    }
}
