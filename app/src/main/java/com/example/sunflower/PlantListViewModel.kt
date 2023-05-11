package com.example.sunflower

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.sunflower.data.PlantViewData
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

    val gardenListState: StateFlow<List<PlantViewData>> =
        savedStateHandle.getStateFlow(GARDEN_LIST, emptyList())

    fun findPlantByName(plantName: String): PlantViewData? =
        plantList.find { plantViewData -> plantViewData.plantName == plantName }

    enum class PlantToGardenResult {
        Success,
        PlantNotFound // To. 수현
                      // 범용적 결과 클래스를 정의하는 것이 필요한지 필요하지 않은지는 딱 한 경우를 보고 판단할 수 없습니다.
                      // 패턴이 누적될 때까지 범용적 결과 클래스를 별도로 정의할 필요는 없습니다.
                      // "실패" 보다는 실패의 원인을 이름으로 표현 해주는 것도 좋은 습관입니다.
    }

    /**
     * Attempts to bed out the given [plant] to the garden.
     *
     * Returns [ResultOfFunction.Success]
     */
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
