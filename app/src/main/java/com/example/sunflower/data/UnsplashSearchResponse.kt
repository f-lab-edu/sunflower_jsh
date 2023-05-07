package com.example.sunflower.data

data class UnsplashSearchResponse(
    val results: List<Photo>,
)

data class Photo(
    val urls: PhotoUrls,
    val description: String,
)

data class PhotoUrls(
    val thumb: String,
)
