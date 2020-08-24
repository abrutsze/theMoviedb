package com.example.entities.localmodels

data class PopularMoveItem(
    val popularity: Double? = null,
    val id: Int? = null,
    val video: Boolean? = null,
    val scoreCount:Int?=null,
    val title: String? = null,
    val releaseDate: String? = null,
    val originalLanguage: String? = null,
    val originalTitle: String? = null,
    val genreIds: List<Int>? = null,
    val backdropPath: String? = null,
    val adult: Boolean? = null,
    val overview: String? = null,
    val posterPath: String? = null
)