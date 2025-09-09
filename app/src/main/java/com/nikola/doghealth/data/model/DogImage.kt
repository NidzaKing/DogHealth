package com.nikola.doghealth.data.model

data class DogImage(
    val id: String,
    val url: String,
    val width: Int? = null,
    val height: Int? = null,
    val breeds: List<Breed>? = null
)
