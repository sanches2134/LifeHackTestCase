package com.alexjudin.lifehacktestcase.data.network

import com.alexjudin.lifehacktestcase.domain.entity.DetailResponseItem
import com.alexjudin.lifehacktestcase.domain.entity.ResponseItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("v2/top-headlines")
    suspend fun getCompany(
    ): Response<Array<ResponseItem>>

}

interface DetailApi {
    @GET("v2/top-headlines")
    suspend fun getDetailCompany(
        @Query("id")
        id: Int = 1
    ): Response<Array<DetailResponseItem>>
}
