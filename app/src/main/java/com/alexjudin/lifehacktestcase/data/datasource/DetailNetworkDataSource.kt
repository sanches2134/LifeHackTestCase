package com.alexjudin.lifehacktestcase.data.datasource

import com.alexjudin.lifehacktestcase.data.network.DetailApi
import com.alexjudin.lifehacktestcase.domain.entity.DetailResponseItem
import retrofit2.Response
import javax.inject.Inject


interface DetailNetworkDataSource {
    suspend fun getDataById(id: Int): Response<Array<DetailResponseItem>>
}

class DetailNetworkDataSourceImpl @Inject constructor(
    private val detailApiClient: DetailApi
) : DetailNetworkDataSource {


    override suspend fun getDataById(id: Int): Response<Array<DetailResponseItem>> =
        detailApiClient.getDetailCompany(id)


}