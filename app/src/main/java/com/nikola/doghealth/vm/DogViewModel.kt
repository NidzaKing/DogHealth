package com.nikola.doghealth.vm

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import com.nikola.doghealth.data.DogRepository
import com.nikola.doghealth.data.model.Breed
import com.nikola.doghealth.data.model.DogImage

class DogViewModel : ViewModel() {
    private val repo = DogRepository()

    private val _breeds = MutableLiveData<List<Breed>>()
    val breeds: LiveData<List<Breed>> = _breeds

    private val _images = MutableLiveData<List<DogImage>>()
    val images: LiveData<List<DogImage>> = _images

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String?>(null)
    val error: LiveData<String?> = _error

    fun loadBreeds() {
        _loading.value = true
        viewModelScope.launch {
            try {
                _breeds.value = repo.getBreeds()
                _error.value = null
            } catch (e: Exception) {
                _error.value = e.message
            } finally { _loading.value = false }
        }
    }

    fun searchBreeds(query: String) {
        if (query.isBlank()) { loadBreeds(); return }
        _loading.value = true
        viewModelScope.launch {
            try {
                _breeds.value = repo.searchBreeds(query)
                _error.value = null
            } catch (e: Exception) {
                _error.value = e.message
            } finally { _loading.value = false }
        }
    }

    fun loadBreedImages(breedId: Int) {
        _loading.value = true
        viewModelScope.launch {
            try {
                _images.value = repo.getImagesByBreed(breedId)
                _error.value = null
            } catch (e: Exception) {
                _error.value = e.message
            } finally { _loading.value = false }
        }
    }

    fun loadRandomDog() {
        _loading.value = true
        viewModelScope.launch {
            try {
                _images.value = repo.getRandomImage()
                _error.value = null
            } catch (e: Exception) {
                _error.value = e.message
            } finally { _loading.value = false }
        }
    }
}