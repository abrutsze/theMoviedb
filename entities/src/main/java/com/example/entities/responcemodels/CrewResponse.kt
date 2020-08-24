package com.example.entities.responcemodels

import com.squareup.moshi.Json

data class CrewResponse(
    @field:Json(name = "credit_id")
    val creditId: String? = null,
    @field:Json(name = "department")
    val department: String? = null,
    @field:Json(name = "gender")
    val gender: Int? = null,
    @field:Json(name = "id")
    val id: Int? = null,
    @field:Json(name = "job")
    val job: String? = null,
    @field:Json(name = "name")
    val name: String? = null,
    @field:Json(name = "profile_path")
    val profilePath: String? = null
)