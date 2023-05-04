package com.example.sunflower

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.sunflower.data.PlantViewData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PlantListViewModel() : ViewModel() {
    private val _plantListState: MutableStateFlow<MutableList<PlantViewData>> = MutableStateFlow(mutableListOf())

    val plantListState: StateFlow<List<PlantViewData>> = _plantListState.asStateFlow()

    private val _gardenListState: MutableStateFlow<MutableList<PlantViewData>> = MutableStateFlow(mutableListOf())

    val gardenListState: StateFlow<List<PlantViewData>> = _gardenListState.asStateFlow()

    fun init() {
        _plantListState.value = mutableListOf<PlantViewData>(
            PlantViewData(R.drawable.img_apple, "이름1", "3", "설명", "심은날짜", "물준날짜", false, "출처"),
            PlantViewData(R.drawable.img_apple, "이름2", "4", "설명", "심은날짜", "물준날짜", false, "출처"),
            PlantViewData(R.drawable.img_apple, "이름3", "5", "설명", "심은날짜", "물준날짜", false, "출처"),
            PlantViewData(R.drawable.img_apple, "이름4", "6", "설명", "심은날짜", "물준날짜", false, "출처"),
            PlantViewData(R.drawable.img_apple, "이름5", "7", "설명", "심은날짜", "물준날짜", false, "출처"),
            PlantViewData(R.drawable.img_apple, "이름6", "8", "설명", "심은날짜", "물준날짜", false, "출처"),
            PlantViewData(R.drawable.img_apple, "이름7", "9", "설명", "심은날짜", "물준날짜", false, "출처"),
            PlantViewData(R.drawable.img_apple, "이름8", "1", "설명", "심은날짜", "물준날짜", false, "출처"),
        )
    }

    fun addPlantToGarden(idx: Int, context: Context) {
        _plantListState.value[idx] = _plantListState.value[idx].copy(isPlanted = true)
        _gardenListState.value.add(_plantListState.value[idx])
        ToastUtil.addPlantToast(context)
    }

    fun checkAdded(idx: Int): Boolean = _plantListState.value[idx].isPlanted
}
