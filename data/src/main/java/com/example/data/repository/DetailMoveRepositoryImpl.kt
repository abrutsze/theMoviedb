package com.example.data.repository

import com.example.data.dataservice.apiservice.AllApiService
import com.example.data.datastore.DetailMoveRepository
import com.example.data.utils.analyzeResponse
import com.example.data.utils.makeApiCall
import com.example.entities.localmodels.QueryDetailMoveDataBody
import retrofit2.Response
import com.example.entities.Result
import com.example.entities.localmodels.QueryTopCastMoveDataBody
import com.example.entities.responcemodels.DetailMoveResponse
import com.example.entities.responcemodels.TopCastResponse

class DetailMoveRepositoryImpl(private val allApiService: AllApiService) : DetailMoveRepository {
    override suspend fun getDetailMoveData(
        queryBody: QueryDetailMoveDataBody
    ): Result<DetailMoveResponse> =
        makeApiCall({
            getDetailMoveData(
                allApiService.getDetailData(
                    queryBody.moveId,
                    queryBody.apiKey

                )
            )
        })

    private fun getDetailMoveData(popularMove: Response<DetailMoveResponse>): Result<DetailMoveResponse> =
        analyzeResponse(popularMove)

    override suspend fun getTopCastData(queryBody: QueryTopCastMoveDataBody): Result<TopCastResponse> =
        makeApiCall({
            getTopCastMoveData(
                allApiService.getTopCastData(
                    queryBody.moveId,
                    queryBody.apiKey

                )
            )
        })

    private fun getTopCastMoveData(popularMove: Response<TopCastResponse>): Result<TopCastResponse> =
        analyzeResponse(popularMove)

}