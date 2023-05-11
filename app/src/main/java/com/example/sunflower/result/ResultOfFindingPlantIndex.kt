package com.example.sunflower.result

sealed class ResultOfFindingPlantIndex {
    data class Success(val index: Int) : ResultOfFindingPlantIndex()
    object Failure : ResultOfFindingPlantIndex()
}
