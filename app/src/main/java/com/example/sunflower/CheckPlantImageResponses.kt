package com.example.sunflower

import android.util.Log
import com.example.sunflower.data.UnsplashSearchResponse
import com.example.sunflower.network.ApiResult

private const val TAG = "Unsplash API"

class CheckPlantImageResponses(
    private val responses: List<ApiResult<UnsplashSearchResponse>>,
    private val setPlantImage: (index: Int, imageUrl: String) -> Unit,
) {
    operator fun invoke() {
        responses.forEachIndexed { index, response ->
            attemptToSetPlantImage(index, response, setPlantImage)
        }
    }
}

fun attemptToSetPlantImage(
    index: Int,
    response: ApiResult<UnsplashSearchResponse>,
    setPlantImage: (index: Int, imageUrl: String) -> Unit,
) {
    when (response) {
        is ApiResult.Success -> {
            val imageUrl = response.value.results[0].urls.thumb
            setPlantImage(index, imageUrl)
        }
        is ApiResult.Failure -> Log.e(TAG, response.message.toString())
    }
}
