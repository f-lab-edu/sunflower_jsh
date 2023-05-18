package com.example.sunflower.network

import com.example.sunflower.data.WikipediaResponse
import com.example.sunflower.network.GsonConverter.gSonConverter
import com.example.sunflower.network.OkHttpClient.client
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

interface WikipediaService {

    @GET("page/summary/{title}")
    suspend fun searchPlantDescription(
        @Path("title") searchTerm: String?,
    ): WikipediaResponse

    companion object {
        private const val BASE_URL = "https://en.wikipedia.org/api/rest_v1/"

        val wikipediaService: WikipediaService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(gSonConverter)
            .build()
            .create(WikipediaService::class.java)
    }
}
