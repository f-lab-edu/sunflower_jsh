package com.example.sunflower

import android.util.Log
import com.example.sunflower.data.PlantViewData
import com.example.sunflower.data.UnsplashSearchResponse
import com.example.sunflower.data.WikipediaResponse
import com.example.sunflower.network.ApiResult

private const val TAG = "Unsplash API"
private const val NULL_WARNING = "Sorry Something is wrong"

class CheckPlantResponses(
    private val plantList: List<PlantViewData>,
    private val unsplashResponses: List<ApiResult<UnsplashSearchResponse>>,
    private val wikipediaResponses: List<ApiResult<WikipediaResponse>>,
    private val setPlant: (index: Int, imageUrl: String, plantDescription: String) -> Unit,
) {
    operator fun invoke() {
        for (index in plantList.indices) {
            attemptToSetPlant(index, unsplashResponses[index], wikipediaResponses[index], setPlant)
        }
    }
}

fun attemptToSetPlant(
    index: Int,
    unsplashResponse: ApiResult<UnsplashSearchResponse>,
    wikipediaResponse: ApiResult<WikipediaResponse>,
    setPlant: (index: Int, imageUrl: String, plantDescription: String) -> Unit,
) {
    val imageUrl = unwrapUnsplashResponse(unsplashResponse)
    val plantDescription = unwrapWikipediaResponse(wikipediaResponse)
    setPlant(index, imageUrl, plantDescription)
}

private fun unwrapUnsplashResponse(unsplashResponse: ApiResult<UnsplashSearchResponse>): String {
    return when (unsplashResponse) {
        is ApiResult.Success -> {
            unsplashResponse.value.results[0].urls.thumb
        }
        is ApiResult.Failure -> {
            Log.e(TAG, unsplashResponse.message.toString())
            NULL_WARNING
        }
    }
}

private fun unwrapWikipediaResponse(wikipediaResponse: ApiResult<WikipediaResponse>): String {
    return when (wikipediaResponse) {
        is ApiResult.Success -> {
            wikipediaResponse.value.extract
        }
        is ApiResult.Failure -> {
            Log.e(TAG, wikipediaResponse.message.toString())
            NULL_WARNING
        }
    }
}
