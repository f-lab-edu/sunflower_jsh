package com.example.sunflower

import androidx.annotation.DrawableRes


data class PlantInfo(
    @DrawableRes val image: Int,
    val name: String,
    val wateringCycle: String,
    val explanation: String,
    val planted: String,
    val lastWatered: String,
)
