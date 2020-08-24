package com.example.domain.interactors

import com.example.entities.Result
import com.example.entities.localmodels.DetailMove
import com.example.entities.localmodels.TopCast

interface DetailMoveInteractor {
    suspend fun getDetailMoveData(moveId:Int): Result<DetailMove>
    suspend fun getTopCastMoveData(moveId:Int): Result<TopCast>
}