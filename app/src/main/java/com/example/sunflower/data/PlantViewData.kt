package com.example.sunflower.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlantViewData(
    val plantName: String,
    val imageUrl: String,
    val wateringCycle: String,
    val explanation: String,
    val plantedDate: String,
    val lastWateredDate: String,
    val isPlanted: Boolean,
    val sourceLink: String,
) : Parcelable
