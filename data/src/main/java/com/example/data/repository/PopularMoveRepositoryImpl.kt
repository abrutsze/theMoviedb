package com.example.data.repository

import com.example.data.dataservice.apiservice.AllApiService
import com.example.data.datastore.PopularMoveRepository
import com.example.data.utils.analyzeResponse
import com.example.data.utils.makeApiCall
import com.example.entities.PopularMoveData
import com.example.entities.localmodels.QueryPopularDataBody
import retrofit2.Response
import com.example.entities.Result

class PopularMoveRepositoryImpl(private val allApiService: AllApiService) : PopularMoveRepository {
    override suspend fun getPopularMoveData(
        queryBody: QueryPopularDataBody
    ): Result<PopularMoveData> =
        makeApiCall({
            getPopularMoveData(
                allApiService.getPopularList(
                    queryBody.apiKey,
                    queryBody.language,
                    queryBody.page
                )
            )
        })
    private fun getPopularMoveData(popularMove: Response<PopularMoveData>): Result<PopularMoveData> =
        analyzeResponse(popularMove)
}