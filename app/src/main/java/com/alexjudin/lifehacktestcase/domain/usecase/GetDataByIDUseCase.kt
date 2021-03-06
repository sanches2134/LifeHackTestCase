package com.alexjudin.lifehacktestcase.domain.usecase

import com.alexjudin.lifehacktestcase.domain.entity.DetailResponseItem
import com.alexjudin.lifehacktestcase.domain.repository.CompanyRepository
import retrofit2.Response
import javax.inject.Inject

class GetDataByIDUseCase @Inject constructor(private val companyRepository: CompanyRepository) {
    suspend operator fun invoke(id: Int): Response<Array<DetailResponseItem>> =
        companyRepository.getCompanyById(id)
}