package com.example.entities.localmodels

data class QueryTopCastMoveDataBody (
    private val _apiKey: String,
    private val _moveId: Int,
) {
    val apiKey: String
        get() = _apiKey
    val moveId: Int
        get() = _moveId
}