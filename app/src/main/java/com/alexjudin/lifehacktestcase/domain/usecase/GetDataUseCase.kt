package com.alexjudin.lifehacktestcase.domain.usecase

import com.alexjudin.lifehacktestcase.domain.entity.ResponseItem
import com.alexjudin.lifehacktestcase.domain.repository.CompanyRepository
import retrofit2.Response
import javax.inject.Inject

class GetDataUseCase @Inject constructor(private val companyRepository: CompanyRepository) {
    suspend operator fun invoke(): Response<Array<ResponseItem>> =
        companyRepository.getListCompany()
}
