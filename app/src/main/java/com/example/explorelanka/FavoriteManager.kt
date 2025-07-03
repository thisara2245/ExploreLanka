package com.example.explorelanka

object FavoriteManager {
    val favoriteList = mutableListOf<Destination>()

    fun addToFavorites(destination: Destination) {
        if (!favoriteList.contains(destination)) {
            favoriteList.add(destination)
        }
    }

    fun getFavorites(): List<Destination> = favoriteList
}
