package com.alexjudin.lifehacktestcase.di.module

import com.alexjudin.lifehacktestcase.data.datasource.DetailNetworkDataSource
import com.alexjudin.lifehacktestcase.data.datasource.DetailNetworkDataSourceImpl
import com.alexjudin.lifehacktestcase.data.datasource.NetworkCompanyDataSourceImpl
import com.alexjudin.lifehacktestcase.data.datasource.NetworkCompanyDataSource
import com.alexjudin.lifehacktestcase.data.network.Api
import com.alexjudin.lifehacktestcase.data.network.Constants.BASE_URL
import com.alexjudin.lifehacktestcase.data.network.DetailApi
import com.alexjudin.lifehacktestcase.data.repository.CompanyRepositoryImpl
import com.alexjudin.lifehacktestcase.domain.repository.CompanyRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun provideCompanyRepository(
        networkCompanyDataSource: NetworkCompanyDataSource,
        detailNetworkDataSource: DetailNetworkDataSource
    ): CompanyRepository =
        CompanyRepositoryImpl(networkCompanyDataSource, detailNetworkDataSource)

    @Singleton
    @Provides
    fun provideNetworkDataSource(api: Api): NetworkCompanyDataSource =
        NetworkCompanyDataSourceImpl(api)

    @Singleton
    @Provides
    fun provideSingleNetworkDataSource(detailApi: DetailApi): DetailNetworkDataSource =
        DetailNetworkDataSourceImpl(detailApi)


    @Singleton
    @Provides
    fun provideSingleCompanyApiClient(): DetailApi {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(DetailApi::class.java)
    }

    @Singleton
    @Provides
    fun provideCompanyApiClient(): Api {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(Api::class.java)
    }

}