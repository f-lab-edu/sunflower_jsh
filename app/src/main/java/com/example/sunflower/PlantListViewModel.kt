package com.example.sunflower

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sunflower.data.PlantViewData
import com.example.sunflower.network.ApiResult
import com.example.sunflower.network.UnsplashService
import com.example.sunflower.network.WikipediaService
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.time.LocalDate

private const val PLANT_LIST = "plantList"
private const val GARDEN_LIST = "gardenList"

class PlantListViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    val plantListState: StateFlow<List<PlantViewData>> = savedStateHandle.getStateFlow(
        PLANT_LIST,
        emptyList(),
    )

    val gardenListState: StateFlow<List<PlantViewData>> = savedStateHandle.getStateFlow(
        GARDEN_LIST,
        emptyList(),
    )

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

    init {
        plantList = listOf(
            PlantViewData("Sunflower", imageUrl = "", "3"),
            PlantViewData("Rose", imageUrl = "", "4"),
            PlantViewData("Hydrangea", imageUrl = "", "5"),
            PlantViewData("Gerbera", imageUrl = "", "6"),
        )

        callPlantData()
    }

    private fun callPlantData() {
        viewModelScope.launch {
            val unsplashResponses =
                plantList.map { plant -> async { callPlantImage(plant) } }.awaitAll()

            val wikipediaResponses =
                plantList.map { plant -> async { callPlantDescription(plant) } }.awaitAll()

            CheckPlantResponses(
                plantList,
                unsplashResponses,
                wikipediaResponses,
                this@PlantListViewModel::setPlant,
            ).invoke()
        }
    }

    private suspend fun callPlantDescription(plant: PlantViewData) = try {
        ApiResult.Success(
            WikipediaService.wikipediaService.searchPlantDescription(
                plant.plantName,
            ),
        )
    } catch (e: Exception) {
        ApiResult.Failure(e.message)
    }

    private suspend fun callPlantImage(plant: PlantViewData) = try {
        ApiResult.Success(
            UnsplashService.unsplashService
                .searchPhotos(
                    BuildConfig.UNSPLASH_API_KEY,
                    plant.plantName,
                ),
        )
    } catch (e: Exception) {
        ApiResult.Failure(e.message)
    }

    private fun setPlant(index: Int, imageUrl: String, plantDescription: String) {
        val initializedPlant =
            plantList[index].copy(imageUrl = imageUrl, description = plantDescription)
        val newPlantList = plantList.toMutableList().apply {
            this[index] = initializedPlant
        }
        plantList = newPlantList
    }

    fun findPlantByNameAsFlow(plantName: String): Flow<PlantViewData?> =
        plantListState.map { it.findByName(plantName) }

    private fun List<PlantViewData>.findByName(plantName: String): PlantViewData? =
        find { plantViewData -> plantViewData.plantName == plantName }

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

        val plantedClone =
            currentPlantList[index].copy(isPlanted = true, plantedDate = LocalDate.now().toString())
        val newPlantList = currentPlantList.toMutableList().apply {
            this[index] = plantedClone
        }

        plantList = newPlantList
        gardenList = gardenList + plantedClone
        return PlantToGardenResult.Success
    }
}
