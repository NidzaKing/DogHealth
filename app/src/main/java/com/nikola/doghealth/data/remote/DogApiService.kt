package com.nikola.doghealth.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import com.nikola.doghealth.data.model.Breed
import com.nikola.doghealth.data.model.DogImage
import com.nikola.doghealth.BuildConfig

interface DogApiService {
    @GET("v1/breeds")
    suspend fun getBreeds(
        @Query("api_key") apiKey: String = BuildConfig.DOG_API_KEY
    ): List<Breed>

    @GET("v1/breeds/search")
    suspend fun searchBreeds(
        @Query("q") query: String,
        @Query("api_key") apiKey: String = BuildConfig.DOG_API_KEY
    ): List<Breed>

    @GET("v1/images/search")
    suspend fun getImagesByBreed(
        @Query("breed_ids") breedIds: String,
        @Query("limit") limit: Int = 10,
        @Query("api_key") apiKey: String = BuildConfig.DOG_API_KEY
    ): List<DogImage>

    @GET("v1/images/search")
    suspend fun getRandomImage(
        @Query("limit") limit: Int = 1,
        @Query("api_key") apiKey: String = BuildConfig.DOG_API_KEY
    ): List<DogImage>
}
