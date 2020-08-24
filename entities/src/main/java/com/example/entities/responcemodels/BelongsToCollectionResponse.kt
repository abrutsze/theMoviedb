package com.example.entities.responcemodels

import com.squareup.moshi.Json


data class BelongsToCollectionResponse(
    @field:Json(name = "id")
    val  id: Int? = null,
    @field:Json(name = "name")
    val  name: String? = null,
    @field:Json(name = "poster_path")
    val  posterPath: String? = null,
    @field:Json(name = "backdrop_path")
    val  backdropPath: String? = null
)
