package com.example.data.datastore

import com.example.entities.PopularMoveData
import com.example.entities.Result
import com.example.entities.localmodels.QueryDetailMoveDataBody
import com.example.entities.localmodels.QueryPopularDataBody
import com.example.entities.localmodels.QueryTopCastMoveDataBody
import com.example.entities.responcemodels.DetailMoveResponse
import com.example.entities.responcemodels.TopCastResponse

interface DetailMoveRepository {
    suspend fun getDetailMoveData(queryBody: QueryDetailMoveDataBody): Result<DetailMoveResponse>
    suspend fun getTopCastData(queryBody: QueryTopCastMoveDataBody): Result<TopCastResponse>
}