package com.example.sunflower

import androidx.annotation.DrawableRes

data class PlantData(
    @DrawableRes val image: Int,
    val name: String,
    val wateringCycle: String,
    val explanation: String,
    val plantedDate: String,
    val lastWateredDate: String,
)
