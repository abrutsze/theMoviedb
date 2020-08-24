package com.example.entities.responcemodels

import com.squareup.moshi.Json


data class ProductionCountryResponse(
    @field:Json(name = "iso_3166_1")
    val iso31661: String? = null,
    @field:Json(name = "name")
    val name: String? = null
)