package com.example.entities.responcemodels

import com.squareup.moshi.Json


data class CastResponse(
    @field:Json(name = "cast_id")
    val castId: Int? = null,
    @field:Json(name = "character")
    val character: String? = null,
    @field:Json(name = "credit_id")
    val creditId: String? = null,
    @field:Json(name = "gender")
    val gender: Int? = null,
    @field:Json(name = "id")
    val id: Int? = null,
    @field:Json(name = "name")
    val name: String? = null,
    @field:Json(name = "order")
    val order: Int? = null,
    @field:Json(name = "profile_path")
    val profilePath: String? = null
)