package com.example.sunflower

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.sunflower.data.PlantViewData
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

    fun findPlantByName(plantName: String): PlantViewData? =
        plantList.find { plantViewData -> plantViewData.plantName == plantName }

    @Composable
    fun plantAsState(plant: PlantViewData): PlantViewData? {
        val index = plantList.indexOfFirst { it.plantName == plant.plantName }
        if (index < 0) return null
        return plantListState.collectAsState().value[index]
    }

    enum class PlantToGardenResult {
        Success,
        PlantNotFound,
    }
    fun plantToGarden(plant: PlantViewData): PlantToGardenResult {
        val currentPlantList = plantList
        val index = currentPlantList.indexOfFirst { it.plantName == plant.plantName }
        if (index < 0) {
            return PlantToGardenResult.PlantNotFound
        }

        val plantedClone = currentPlantList[index].copy(isPlanted = true)
        val newPlantList = currentPlantList.toMutableList().apply {
            this[index] = plantedClone
        }

        plantList = newPlantList
        gardenList = gardenList + plantedClone
        return PlantToGardenResult.Success
    }

    private var plantList: List<PlantViewData>
        get() = savedStateHandle.get<List<PlantViewData>>(PLANT_LIST).orEmpty()
        set(value) {
            savedStateHandle[PLANT_LIST] = value
        }

    private var gardenList: List<PlantViewData>
        get() = savedStateHandle.get<List<PlantViewData>>(GARDEN_LIST).orEmpty()
        set(value) {
            savedStateHandle[GARDEN_LIST] = value
        }
}
