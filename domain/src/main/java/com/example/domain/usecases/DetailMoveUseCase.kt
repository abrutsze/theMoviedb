package com.example.domain.usecases

import com.example.data.datastore.DetailMoveRepository
import com.example.entities.Constants.Companion.API_KEY
import com.example.domain.interactors.DetailMoveInteractor
import com.example.domain.utils.toCast
import com.example.entities.Constants.Companion.ERROR_NULL_DATA
import com.example.domain.utils.toDetailMove
import com.example.entities.Result
import com.example.entities.MoveAppException
import com.example.entities.localmodels.DetailMove
import com.example.entities.localmodels.QueryDetailMoveDataBody
import com.example.entities.localmodels.QueryTopCastMoveDataBody
import com.example.entities.localmodels.TopCast
import com.example.entities.responcemodels.TopCastResponse

class DetailMoveUseCase(private val detailMoveRepository: DetailMoveRepository) :
    DetailMoveInteractor {
    override suspend fun getDetailMoveData(moveId: Int): Result<DetailMove> {
        val apiData = detailMoveRepository.getDetailMoveData(
            QueryDetailMoveDataBody(
                API_KEY, moveId
            )
        )

        return when (apiData) {
            is Result.Success -> {
                val mappingData = apiData.data?.toDetailMove()
                Result.Success(mappingData)
            }
            else -> {
                Result.Error(MoveAppException(ERROR_NULL_DATA, null, "Can't load move detail data into server"))
            }
        }
    }

    override suspend fun getTopCastMoveData(moveId: Int): Result<TopCast> {
        val apiData = detailMoveRepository.getTopCastData(
            QueryTopCastMoveDataBody(
                API_KEY, moveId
            )
        )
        return when (apiData) {
            is Result.Success -> {
                val mappingData = apiData.data?.toCast()
                Result.Success(mappingData)
            }
            else -> {
                Result.Error(MoveAppException(ERROR_NULL_DATA, null, "Can't load top cast data into server"))
            }
        }
    }
}