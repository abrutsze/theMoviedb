package com.example.data.dataservice.apiservice

import com.example.entities.PopularMoveData
import com.example.entities.responcemodels.DetailMoveResponse
import com.example.entities.responcemodels.TopCastResponse

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AllApiService {

    @GET("/3/movie/popular")
    suspend fun getPopularList(
        @Query("api_key") key: String,
        @Query("language") language: String,
        @Query("page") page: Int,
    ): Response<PopularMoveData>

    @GET("/3/movie/{id}")
    suspend fun getDetailData(
        @Path("id")  movieId:Int,
        @Query("api_key") key: String
    ): Response<DetailMoveResponse>

    @GET("/3/movie/{id}/casts")
    suspend fun getTopCastData(
        @Path("id")  movieId:Int,
        @Query("api_key") key: String
    ): Response<TopCastResponse>

}
