package com.example.sunflower.network

import com.example.sunflower.data.UnsplashSearchResponse
import com.example.sunflower.network.GsonConverter.gSonConverter
import com.example.sunflower.network.OkHttpClient.client
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashService {
    @GET("search/photos")
    suspend fun searchPhotos(
        @Query("client_id") clientId: String,
        @Query("query") plantName: String,
    ): UnsplashSearchResponse

    companion object {
        private const val BASE_URL = "https://api.unsplash.com/"

        val unsplashService: UnsplashService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(gSonConverter)
            .build()
            .create(UnsplashService::class.java)
    }
}
