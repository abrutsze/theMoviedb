package com.example.data.utils

import com.example.entities.MoveAppException
import java.lang.Exception
import com.example.entities.Result
import retrofit2.Response

suspend fun <R> makeApiCall(
    call: suspend () -> Result<R>,
    errorMessage: Int = 4567
) = try {
    call()
} catch (e: Exception) {
    Result.Error(MoveAppException<Nothing>(errorMessage))
}

fun <R> analyzeResponse(response: Response<R>): Result<R> {
    return when (response.code()) {
        200 -> {
            val responseBody = response.body()
            Result.Success(responseBody)
        }
        else -> {
            Result.Error(MoveAppException<Nothing>(response.code()))
        }
    }
}
