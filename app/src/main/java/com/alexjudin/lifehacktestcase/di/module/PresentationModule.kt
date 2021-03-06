package com.alexjudin.lifehacktestcase.di.module

import android.content.Context
import com.alexjudin.lifehacktestcase.domain.usecase.GetDataByIDUseCase
import com.alexjudin.lifehacktestcase.domain.usecase.GetDataUseCase
import com.alexjudin.lifehacktestcase.presentation.viewmodel.CompanyViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PresentationModule {

    @Singleton
    @Provides
    fun provideCompanyViewModel(
        getDataUseCase: GetDataUseCase,
        getDataByIDUseCase: GetDataByIDUseCase,
        context: Context
    ): CompanyViewModel = CompanyViewModel(
        getDataUseCase,
        getDataByIDUseCase,
        context
    )
}