package com.example.entities.responcemodels

import com.squareup.moshi.Json


data class SpokenLanguageResponse(
    @field:Json(name = "iso_639_1")
    val iso6391: String? = null,
    @field:Json(name = "name")
val name: String? = null
)