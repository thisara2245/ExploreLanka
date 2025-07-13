package com.example.explorelanka

import Destination
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

object FavoriteManager {
    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()
    private val userId get() = auth.currentUser?.uid ?: ""
    val favoriteList = mutableListOf<Destination>()

    fun loadFavorites(onComplete: () -> Unit = {}) {
        if (userId.isEmpty()) return

        firestore.collection("users")
            .document(userId)
            .collection("favorites")
            .get()
            .addOnSuccessListener { result ->
                favoriteList.clear()
                for (doc in result) {
                    val destination = doc.toObject(Destination::class.java)
                    favoriteList.add(destination)
                }
                onComplete()
            }
    }

    fun addToFavorites(destination: Destination) {
        if (userId.isEmpty()) return
        if (favoriteList.any { it.id == destination.id }) return

        firestore.collection("users")
            .document(userId)
            .collection("favorites")
            .document(destination.id)
            .set(destination)
            .addOnSuccessListener {
                favoriteList.add(destination)
            }
    }

    fun removeFromFavorites(destination: Destination) {
        if (userId.isEmpty()) return

        firestore.collection("users")
            .document(userId)
            .collection("favorites")
            .document(destination.id)
            .delete()
            .addOnSuccessListener {
                favoriteList.removeAll { it.id == destination.id }
            }
    }

    fun getFavorites(): List<Destination> = favoriteList
}
