package com.example.sunflower.data

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlantViewData(
    @DrawableRes val imageResId: Int,
    val plantName: String,
    val wateringCycle: String,
    val explanation: String,
    val plantedDate: String,
    val lastWateredDate: String,
    val isPlanted: Boolean,
    val sourceLink: String,
) : Parcelable
