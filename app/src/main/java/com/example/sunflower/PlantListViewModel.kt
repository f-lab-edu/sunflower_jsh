package com.example.sunflower

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.sunflower.data.PlantViewData
import com.example.sunflower.result.ResultOfFindingPlantIndex
import com.example.sunflower.result.ResultOfFunction
import kotlinx.coroutines.flow.StateFlow

private const val PLANT_LIST = "plantList"
private const val GARDEN_LIST = "gardenList"

class PlantListViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    val plantListState: StateFlow<List<PlantViewData>> = savedStateHandle.getStateFlow(
        PLANT_LIST,
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

    val gardenListState: StateFlow<List<PlantViewData>> = savedStateHandle.getStateFlow(
        GARDEN_LIST,
        emptyList(),
    )

    fun findSelectedPlantIndex(plantName: String): ResultOfFindingPlantIndex {
        for (index in plantListState.value.indices) {
            if (plantListState.value[index].plantName == plantName) {
                return ResultOfFindingPlantIndex.Success(
                    index,
                )
            }
        }
        return ResultOfFindingPlantIndex.Failure
    }

    fun addPlantToGarden(selectedPlantName: String): ResultOfFunction {
        val currentPlantList = plantListState.value
        val newPlantList = currentPlantList.toMutableList()
        for (index in currentPlantList.indices) {
            if (currentPlantList[index].plantName == selectedPlantName) {
                val targetPlant = currentPlantList[index].copy(isPlanted = true)
                newPlantList[index] = targetPlant
                savedStateHandle[PLANT_LIST] = newPlantList
                savedStateHandle[GARDEN_LIST] = gardenListState.value + targetPlant
                return ResultOfFunction.Success
            }
        }
        return ResultOfFunction.Failure
    }
}
