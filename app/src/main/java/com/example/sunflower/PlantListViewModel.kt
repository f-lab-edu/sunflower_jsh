package com.example.sunflower

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.sunflower.data.PlantViewData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.jetbrains.annotations.Nullable

class PlantListViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    private var _plantListState: MutableStateFlow<List<PlantViewData>> = MutableStateFlow(
        listOf(
            PlantViewData(R.drawable.img_apple, "이름1", "3", "설명", "심은날짜", "물준날짜", false, "출처"),
            PlantViewData(R.drawable.img_apple, "이름2", "4", "설명", "심은날짜", "물준날짜", false, "출처"),
            PlantViewData(R.drawable.img_apple, "이름3", "5", "설명", "심은날짜", "물준날짜", false, "출처"),
            PlantViewData(R.drawable.img_apple, "이름4", "6", "설명", "심은날짜", "물준날짜", false, "출처"),
            PlantViewData(R.drawable.img_apple, "이름5", "7", "설명", "심은날짜", "물준날짜", false, "출처"),
            PlantViewData(R.drawable.img_apple, "이름6", "8", "설명", "심은날짜", "물준날짜", false, "출처"),
            PlantViewData(R.drawable.img_apple, "이름7", "9", "설명", "심은날짜", "물준날짜", false, "출처"),
            PlantViewData(R.drawable.img_apple, "이름8", "1", "설명", "심은날짜", "물준날짜", false, "출처"),
        ),
    )

    val plantListState: StateFlow<List<PlantViewData>> = _plantListState.asStateFlow()

    private var _gardenListState: MutableStateFlow<List<PlantViewData>> = MutableStateFlow(emptyList())

    val gardenListState: StateFlow<List<PlantViewData>> = _gardenListState.asStateFlow()

    init {
        for (plantViewData in plantListState.value) {
            savedStateHandle[plantViewData.plantName] = plantViewData
        }
    }

    fun findSelectedPlantViewData(plantName: String): PlantViewData? {
        return savedStateHandle.get<PlantViewData>(plantName)
    }

    fun addPlantToGarden(selectedPlantName: String, context: Context) {
        val currentPlantList = _plantListState.value
        val newPlantList = currentPlantList.toMutableList()
        savedStateHandle.get<PlantViewData>(selectedPlantName)?.let { plantViewData ->
            savedStateHandle[selectedPlantName] = plantViewData.copy(isPlanted = true)
        }
        for (index in currentPlantList.indices) {
            if (currentPlantList[index].plantName == selectedPlantName) {
                val targetPlant = currentPlantList[index].copy(isPlanted = true)
                newPlantList[index] = targetPlant
                _plantListState.value = newPlantList
                _gardenListState.value = _gardenListState.value + targetPlant
                Toast.makeText(context, R.string.add_toast_message, Toast.LENGTH_SHORT).show()
                break
            }
        }
    }

    @Nullable
    fun checkIsPlantPlanted(plantName: String, context: Context): Boolean {
        if (savedStateHandle.get<PlantViewData>(plantName) == null) {
            Toast.makeText(
                context,
                context.getString(R.string.error_message),
                Toast.LENGTH_SHORT,
            ).show()
        }
        return savedStateHandle.get<PlantViewData>(plantName)?.isPlanted ?: false
    }
}
