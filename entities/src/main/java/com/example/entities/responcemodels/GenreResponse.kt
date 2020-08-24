package com.example.entities.responcemodels

import com.squareup.moshi.Json


data class GenreResponse(
    @field:Json(name = "id")
    val id: Int? = null,
    @field:Json(name = "name")
    val name: String? = null
)