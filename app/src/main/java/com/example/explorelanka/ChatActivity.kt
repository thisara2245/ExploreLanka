package com.example.explorelanka

import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ChatActivity : AppCompatActivity() {

    private lateinit var chatLayout: LinearLayout
    private lateinit var messageInput: EditText
    private lateinit var sendButton: Button
    private lateinit var scrollView: ScrollView
    private lateinit var chatbotName: TextView
    private lateinit var onlineStatusDot: View
    private lateinit var onlineStatusText: TextView
    private lateinit var backButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)



        backButton = findViewById(R.id.btnBack1)
        chatLayout = findViewById(R.id.chatLayout)
        messageInput = findViewById(R.id.messageInput)
        sendButton = findViewById(R.id.sendButton)
        scrollView = findViewById(R.id.scrollView)


        chatbotName = findViewById(R.id.chatbotName)
        onlineStatusDot = findViewById(R.id.onlineStatusDot)
        onlineStatusText = findViewById(R.id.onlineStatusText)

        chatbotName.text = "ExploreLanka Bot"

        setOnlineStatus(true)

        sendButton.setOnClickListener {
            val userMessage = messageInput.text.toString().trim()
            if (userMessage.isNotEmpty()) {
                addMessage(userMessage, isUser = true)
                respondToMessage(userMessage)
                messageInput.text.clear()
            }
        }
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("navigate_to", "home")
            startActivity(intent)
            finish()
        }

    }

    private fun respondToMessage(message: String) {
        val response =when {
            message.contains("hello", true) || message.contains("hi", true) ->
                "Hello there! 👋 Looking for travel tips or help planning your journey?"

            message.contains("hotel", true) || message.contains("stay", true) ->
                "Looking for a place to stay? 🏨 We recommend hotels based on location, budget, and ratings. Would you prefer luxury, mid-range, or budget accommodation?"

            message.contains("colombo hotel", true) ->
                "In Colombo, you can check out Cinnamon Grand, Galle Face Hotel, or Marino Beach Hotel. All of them offer great comfort with ocean views. 🌊"

            message.contains("kandy hotel", true) ->
                "Popular hotels in Kandy include Earl's Regency, The Grand Kandyan, and OZO Kandy. They’re close to the Temple of the Tooth Relic. 🛕"

            message.contains("places", true) || message.contains("visit", true) ->
                "Top places to visit include Sigiriya Rock Fortress, Ella, Nuwara Eliya, and Yala National Park. Would you like to explore historical sites, nature, or beaches? 🌴"

            message.contains("sigiriya", true) ->
                "Sigiriya is an ancient rock fortress with breathtaking views. 🪨 It's about 175 km from Colombo. Best time to visit is early morning or late afternoon."

            message.contains("nuwara eliya", true) ->
                "Nuwara Eliya, known as 'Little England', is perfect for a cool escape 🌧️. It's 160 km from Colombo and famous for tea plantations and Gregory Lake."

            message.contains("distance", true) || message.contains("how far", true) ->
                "Tell me the two locations you'd like to check distance between, and I’ll help you with that! 🧭"

            message.contains("map", true) || message.contains("directions", true) ->
                "Tap the 🗺️ Map button to explore routes, nearby attractions, and your current location."

            message.contains("weather", true) ->
                "Want to check the weather before your trip? Let me know the location and date! ☀️🌧️"

            message.contains("thanks", true) || message.contains("thank", true) ->
                "You're most welcome! 😊 Let me know if you have any more questions."

            message.contains("food", true) || message.contains("restaurant", true) ->
                "Craving good food? 🍛 I can recommend restaurants based on your current location or preferences — spicy, vegetarian, street food?"

            else ->
                "Sorry, I didn’t quite catch that. Try asking about hotels, places to visit, weather, distance, or maps! 🧳"
        }

        addMessage(response, isUser = false)
    }

    private fun addMessage(text: String, isUser: Boolean) {
        val textView = TextView(this)
        textView.text = text
        textView.textSize = 16f
        textView.setPadding(20, 12, 20, 12)
        textView.setBackgroundResource(
            if (isUser) R.drawable.user_bubble else R.drawable.bot_bubble
        )

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(0, 8, 0, 8)
        params.gravity = if (isUser) Gravity.END else Gravity.START
        textView.layoutParams = params

        chatLayout.addView(textView)
        scrollView.post { scrollView.fullScroll(ScrollView.FOCUS_DOWN) }
    }

    private fun setOnlineStatus(isOnline: Boolean) {
        if (isOnline) {
            onlineStatusText.text = "Online"
            onlineStatusText.setTextColor(resources.getColor(android.R.color.holo_green_dark))
            setDotColor("#4CAF50")  // Green
        } else {
            onlineStatusText.text = "Offline"
            onlineStatusText.setTextColor(resources.getColor(android.R.color.darker_gray))
            setDotColor("#A9A9A9")  // Gray
        }
    }

    private fun setDotColor(colorHex: String) {
        val bg = onlineStatusDot.background
        if (bg is GradientDrawable) {
            bg.setColor(android.graphics.Color.parseColor(colorHex))
        }
    }

}