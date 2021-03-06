package com.alexjudin.lifehacktestcase.di.module

import com.alexjudin.lifehacktestcase.domain.repository.CompanyRepository
import com.alexjudin.lifehacktestcase.domain.usecase.GetDataUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {

    @Singleton
    @Provides
    fun provideGetDataUseCase(
        companyRepository: CompanyRepository
    ): GetDataUseCase = GetDataUseCase(companyRepository)
}