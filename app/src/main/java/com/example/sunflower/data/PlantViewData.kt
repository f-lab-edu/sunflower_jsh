package com.example.sunflower.data

data class PlantViewData(
    val imageUrl: String,
    val name: String,
    val wateringCycle: String,
    val explanation: String,
    val plantedDate: String,
    val lastWateredDate: String,
    val isPlanted: Boolean,
    val sourceLink: String,
)
