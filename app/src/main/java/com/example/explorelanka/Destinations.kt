package com.example.explorelanka

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Destinations(
    val name: String = "",
    val lat: Double = 0.0,
    val lon: Double = 0.0,
    val imgUrl: String = "",
    val costofonedayPerPerson: Int = 0,
    val description: String = ""

) : Parcelable