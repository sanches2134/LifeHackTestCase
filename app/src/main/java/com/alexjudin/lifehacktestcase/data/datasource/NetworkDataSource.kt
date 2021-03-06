package com.alexjudin.lifehacktestcase.data.datasource

import com.alexjudin.lifehacktestcase.data.network.Api
import com.alexjudin.lifehacktestcase.domain.entity.ResponseItem
import retrofit2.Response
import javax.inject.Inject

interface NetworkCompanyDataSource {
    suspend fun getData(): Response<Array<ResponseItem>>

}

class NetworkCompanyDataSourceImpl @Inject constructor(
    private val newsNewsApiClient: Api
) : NetworkCompanyDataSource {


    override suspend fun getData(): Response<Array<ResponseItem>> =
        newsNewsApiClient.getCompany()

}
