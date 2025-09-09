package com.nikola.doghealth.data

import com.nikola.doghealth.data.remote.DogApiService
import com.nikola.doghealth.data.remote.RetrofitClient

class DogRepository {
    private val api = RetrofitClient.instance.create(DogApiService::class.java)

    suspend fun getBreeds() = api.getBreeds()
    suspend fun searchBreeds(q: String) = api.searchBreeds(q)
    suspend fun getImagesByBreed(breedId: Int, limit: Int = 10) =
        api.getImagesByBreed(breedId.toString(), limit)
    suspend fun getRandomImage() = api.getRandomImage(1)
}