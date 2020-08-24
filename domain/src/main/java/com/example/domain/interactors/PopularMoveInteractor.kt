package com.example.domain.interactors

import com.example.entities.Result
import com.example.entities.localmodels.PopularMoveItem

interface PopularMoveInteractor {
    suspend fun getPopularMoveData(): Result<List<PopularMoveItem>>
}