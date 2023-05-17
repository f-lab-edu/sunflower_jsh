package com.example.sunflower.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlantViewData(
    val plantName: String,
    val imageUrl: String = "",
    val wateringCycle: String = "",
    val description: String = "",
    val plantedDate: String = "",
    val isPlanted: Boolean = false,
    val sourceLink: String = "",
) : Parcelable
