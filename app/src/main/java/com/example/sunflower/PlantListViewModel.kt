package com.example.sunflower

import androidx.lifecycle.ViewModel
import com.example.sunflower.data.MockUpDataList
import com.example.sunflower.data.PlantViewData
import kotlinx.coroutines.flow.*

class PlantListViewModel() : ViewModel() {
    private val _plantListState = MutableStateFlow(MockUpDataList.plantList)

    val plantListState: StateFlow<List<PlantViewData>> = _plantListState.asStateFlow()

    private val _gardenListState: MutableStateFlow<MutableList<PlantViewData>> = MutableStateFlow(mutableListOf())

    val gardenListState: StateFlow<List<PlantViewData>> = _gardenListState.asStateFlow()

    fun init() {
        _plantListState.value = MockUpDataList.plantList
    }

    fun addPlantToGarden(idx: Int, plantViewData: PlantViewData) {
        _plantListState.value[idx] = _plantListState.value[idx].copy(isPlanted = true)
        _gardenListState.value.add(plantViewData)
    }
}
