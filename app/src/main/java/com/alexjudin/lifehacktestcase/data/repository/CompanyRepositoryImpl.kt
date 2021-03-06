package com.alexjudin.lifehacktestcase.data.repository

import com.alexjudin.lifehacktestcase.data.datasource.DetailNetworkDataSource
import com.alexjudin.lifehacktestcase.data.datasource.NetworkCompanyDataSource
import com.alexjudin.lifehacktestcase.domain.entity.DetailResponseItem
import com.alexjudin.lifehacktestcase.domain.entity.ResponseItem
import com.alexjudin.lifehacktestcase.domain.repository.CompanyRepository

import retrofit2.Response
import javax.inject.Inject

class CompanyRepositoryImpl @Inject constructor(
    private val networkCompanyDataSource: NetworkCompanyDataSource,
    private val detailNetworkDataSource: DetailNetworkDataSource
) : CompanyRepository {

    override suspend fun getListCompany(): Response<Array<ResponseItem>> =
        networkCompanyDataSource.getData()

    override suspend fun getCompanyById(id: Int): Response<Array<DetailResponseItem>> =
        detailNetworkDataSource.getDataById(id)


}