package com.example.entities.responcemodels

import com.squareup.moshi.Json


data class ProductionCompanyResponse(
    @field:Json(name = "id")
    val id: Int? = null,
    @field:Json(name = "logo_path")
    val logoPath: String? = null,
    @field:Json(name = "name")
    val name: String? = null,
    @field:Json(name = "origin_country")
    val originCountry: String? = null
)