package com.example.data.datastore

import com.example.entities.PopularMoveData
import com.example.entities.Result
import com.example.entities.localmodels.QueryPopularDataBody

interface PopularMoveRepository {
    suspend fun getPopularMoveData(queryBody: QueryPopularDataBody): Result<PopularMoveData>
}