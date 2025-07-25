package com.example.explorelanka

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NotificationsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var backButton: ImageButton
    private lateinit var notificationAdapter: NotificationAdapter
    private val notificationList = listOf(
        NotificationItem("Chatbox Feature is now your app", "July 11, 2025"),
        NotificationItem("Your profile was updated.", "July 10, 2025"),
        NotificationItem("New destination added near you!", "July 9, 2025")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifications)

        backButton = findViewById(R.id.btnBack1)
        recyclerView = findViewById(R.id.recyclerNotifications)
        recyclerView.layoutManager = LinearLayoutManager(this)

        notificationAdapter = NotificationAdapter(notificationList)
        recyclerView.adapter = notificationAdapter

        // Back Button
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("navigate_to", "home")
            startActivity(intent)
            finish()
        }
    }
}
