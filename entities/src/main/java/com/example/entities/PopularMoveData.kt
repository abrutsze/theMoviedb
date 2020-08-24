package com.example.entities

import com.example.entities.responcemodels.PopularMoveItemResponse
import com.squareup.moshi.Json

data class PopularMoveData(
        @field:Json(name = "page")
        val page: Int?,
        @field:Json(name = "total_results")
        val total_results: Int?,
        @field:Json(name = "total_pages")
        val total_pages: Int?,
        @field:Json(name = "results")
        val results: List<PopularMoveItemResponse>? = null
)