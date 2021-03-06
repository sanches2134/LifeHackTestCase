package com.alexjudin.lifehacktestcase.domain.repository

import com.alexjudin.lifehacktestcase.domain.entity.DetailResponseItem
import com.alexjudin.lifehacktestcase.domain.entity.ResponseItem
import retrofit2.Response

interface CompanyRepository {
    suspend fun getListCompany(): Response<Array<ResponseItem>>

    suspend fun getCompanyById(id: Int): Response<Array<DetailResponseItem>>
}