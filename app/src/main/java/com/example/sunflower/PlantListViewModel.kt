package com.example.sunflower

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sunflower.data.PlantViewData
import com.example.sunflower.network.ApiResult
import com.example.sunflower.network.UnsplashService
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PlantListViewModel() : ViewModel() {

    private val _plantListState: MutableStateFlow<MutableList<PlantViewData>> = MutableStateFlow(mutableListOf())
    val plantListState: StateFlow<List<PlantViewData>> = _plantListState.asStateFlow()

    private val _gardenListState: MutableStateFlow<MutableList<PlantViewData>> = MutableStateFlow(mutableListOf())
    val gardenListState: StateFlow<List<PlantViewData>> = _gardenListState.asStateFlow()

    init {
        _plantListState.value = mutableListOf<PlantViewData>(
            PlantViewData("1", "sunflower", "3", "설명", "심은날짜", "물준날짜", false, "출처"),
            PlantViewData("2", "rose", "4", "설명", "심은날짜", "물준날짜", false, "출처"),
            PlantViewData("3", "hydrangea", "5", "설명", "심은날짜", "물준날짜", false, "출처"),
            PlantViewData("4", "gerbera", "6", "설명", "심은날짜", "물준날짜", false, "출처"),
        )

        viewModelScope.launch {
            val responses = _plantListState.value
                .map { plant ->
                    async {
                        try {
                            ApiResult.Success(
                                UnsplashService.create()
                                    .searchPhotos(
                                        "-ZZk39OlYyafsQlTZYcQBrazPB_OyIIE96xmILraSiQ",
                                        plant.name,
                                    ),
                            )
                        } catch (e: Exception) {
                            ApiResult.Failure(e.message)
                        }
                    }
                }.awaitAll()

            responses.forEachIndexed { i, response ->

                when (response) {
                    is ApiResult.Success -> {
                        if (response.value.results.isNotEmpty()) {
                            val imageUrl = response.value.results[0].urls.thumb
                            _plantListState.value = _plantListState.value.toMutableList().apply {
                                this[i] = this[i].copy(imageUrl = imageUrl)
                            }
                        } else {
                            Log.e(TAG, ERROR_EMPTY_MESSAGE)
                        }
                    }
                    is ApiResult.Failure -> Log.e(TAG, response.message.toString())
                }
            }
        }
    }

    fun addPlantToGarden(idx: Int, context: Context) {
        _plantListState.value[idx] = _plantListState.value[idx].copy(isPlanted = true)
        _gardenListState.value.add(_plantListState.value[idx])
        Toast.makeText(context, R.string.addToastMsg, Toast.LENGTH_SHORT).show()
    }

    fun checkIsPlantPlantedAtIndex(idx: Int): Boolean = _plantListState.value[idx].isPlanted

    companion object {
        const val TAG = "Unsplash API"
        const val ERROR_EMPTY_MESSAGE = "Unsplash API"
    }
}
