package com.example.explorelanka

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
// import androidx.core.content.ContentProviderCompat.requireContext // This import is not needed and removed
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth // Import FirebaseAuth
import com.google.firebase.Firebase // Import Firebase for initialization, assuming you have Firebase setup
import com.google.firebase.initialize

class LoginActivity : AppCompatActivity() {

    // Declare Firebase Auth instance
    private lateinit var auth: FirebaseAuth
    private lateinit var etEmail: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var btnLogin: MaterialButton
    private lateinit var tvRegister: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()
        // Ensure Firebase app is initialized. This line is crucial for Firebase services to work.
        // It's typically initialized once in your Application class or main Activity.
        // If you haven't set up Firebase in your project, you'll need to do that first.
        Firebase.initialize(this)

        initializeViews()
        setupClickListeners()
    }

    /**
     * Initializes all the UI views by finding their respective IDs.
     */
    private fun initializeViews() {
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        tvRegister = findViewById(R.id.tvRegister)
    }

    /**
     * Sets up click listeners for the login button and register text view.
     */
    private fun setupClickListeners() {
        // Set click listener for the Login button
        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim() // Trim to remove leading/trailing spaces
            val password = etPassword.text.toString().trim() // Trim to remove leading/trailing spaces

            // Basic input validation
            if (email.isEmpty()) {
                etEmail.error = "Email is required"
                return@setOnClickListener // Exit the click listener if validation fails
            }
            if (password.isEmpty()) {
                etPassword.error = "Password is required"
                return@setOnClickListener // Exit the click listener if validation fails
            }

            // Call the loginUser function to authenticate with Firebase
            loginUser(email, password)
        }

        // Set click listener for the Register TextView
        tvRegister.setOnClickListener {
            // Navigate to RegisterActivity when "Register Here" is clicked
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    /**
     * Attempts to log in a user with the provided email and password using Firebase Authentication.
     * On successful login, navigates to DashboardActivity.
     * On failure, displays an appropriate error message.
     *
     * @param email The user's email address.
     * @param password The user's password.
     */
    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { authTask ->
                if (authTask.isSuccessful) {
                    // Login successful
                    val user = auth.currentUser
                    // You can perform additional checks here if needed,
                    // e.g., if (user.isEmailVerified) { ... }
                    Toast.makeText(this@LoginActivity, "Login successful for ${user?.email}", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@LoginActivity, DashboardActivity::class.java)
                    startActivity(intent)
                    finish() // Finish LoginActivity so user can't go back to it
                } else {
                    // Login failed
                    Toast.makeText(
                        this@LoginActivity,
                        "Authentication failed: ${authTask.exception?.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }
}