package com.example.sunflower.data

import androidx.annotation.DrawableRes

data class PlantViewData(
    @DrawableRes val image: Int,
    val name: String,
    val wateringCycle: String,
    val explanation: String,
    val plantedDate: String,
    val lastWateredDate: String,
    var isPlanted: Boolean,
    val sourceLink: String,
)
