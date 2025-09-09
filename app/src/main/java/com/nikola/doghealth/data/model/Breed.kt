package com.nikola.doghealth.data.model

data class Breed(
    val id: Int,
    val name: String,
    val temperament: String? = null,
    val life_span: String? = null,
    val bred_for: String? = null,
    val breed_group: String? = null,
    val origin: String? = null,
    val weight: Weight? = null,
    val height: Height? = null,
    val reference_image_id: String? = null
)
