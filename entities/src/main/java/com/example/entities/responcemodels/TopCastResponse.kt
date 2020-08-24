package com.example.entities.responcemodels

import com.example.entities.localmodels.Cast
import com.example.entities.localmodels.Crew
import com.squareup.moshi.Json


data class TopCastResponse(
    @Json(name = "id")
    val id: Int? = null,
    @Json(name = "cast")
    val cast: List<CastResponse>? = null,
    @Json(name = "crew")
    val crew: List<CrewResponse>? = null,
)