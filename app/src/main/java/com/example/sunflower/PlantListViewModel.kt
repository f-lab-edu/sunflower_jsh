package com.example.sunflower

import androidx.lifecycle.ViewModel
import com.example.sunflower.data.MockUpDataList
import com.example.sunflower.data.PlantViewData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PlantListViewModel() : ViewModel() {
    private val _plantListState = MutableStateFlow(MockUpDataList.plantList)

    val plantListState: StateFlow<List<PlantViewData>> = _plantListState.asStateFlow()

    fun init() {
        _plantListState.value = MockUpDataList.plantList
    }
}
