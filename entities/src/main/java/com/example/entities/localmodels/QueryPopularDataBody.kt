package com.example.entities.localmodels

data class QueryPopularDataBody (
    private val _apiKey: String,
    private val _page: Int,
    private val _language: String,
) {
    val apiKey: String
        get() = _apiKey
    val page: Int
        get() = _page
    val language: String
        get() = _language
}