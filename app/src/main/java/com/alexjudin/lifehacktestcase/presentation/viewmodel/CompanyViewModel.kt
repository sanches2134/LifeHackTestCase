package com.alexjudin.lifehacktestcase.presentation.viewmodel

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexjudin.lifehacktestcase.data.network.Resource
import com.alexjudin.lifehacktestcase.domain.entity.DetailResponseItem
import com.alexjudin.lifehacktestcase.domain.entity.ResponseItem
import com.alexjudin.lifehacktestcase.domain.usecase.GetDataByIDUseCase
import com.alexjudin.lifehacktestcase.domain.usecase.GetDataUseCase
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

class CompanyViewModel @Inject constructor(
    private val getDataUseCase: GetDataUseCase,
    private val getDataByIDUseCase: GetDataByIDUseCase,
    private val context: Context
) : ViewModel() {

    val company: MutableLiveData<Resource<Array<ResponseItem>>> = MutableLiveData()
    val detailCompany: MutableLiveData<Resource<Array<DetailResponseItem>>> = MutableLiveData()
    private var companyResponse: Array<ResponseItem>? = null
    private var detailCompanyResponse: Array<DetailResponseItem>? = null

    init {
        getData()
    }

    fun getData() = viewModelScope.launch {
        getDataSafeCallc()
    }

    fun getDetailData(id: Int) = viewModelScope.launch {
        getDataSafeCall(id)

    }

    private suspend fun getDataSafeCall(id: Int) {

        detailCompany.postValue(Resource.Loading())
        try {

            if (checkInternet()) {
                Log.d("TAG","companyResponse.body().toString()")
                val response = getDataByIDUseCase(id)
                Log.d("TAG",response.toString())
                detailCompany.postValue(handleDetailCompanyResponse(response))
                Log.d("TAG", response.toString())
            } else {

                detailCompany.postValue(Resource.Error("Нет подключения к интернету!("))

            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> company.postValue(Resource.Error("Ошибка соединения..."))
                else -> company.postValue(Resource.Error("Ошибка преобразования..."))
            }

        }
    }

    private suspend fun getDataSafeCallc() {
        company.postValue(Resource.Loading())
        try {
            if (checkInternet()) {
                val response = getDataUseCase()
                company.postValue(handleCompanyResponse(response))
            } else {
                company.postValue(Resource.Error("Нет подключения к интернету!("))
                company.postValue(Resource.Error("Нет подключения к интернету!("))

            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> company.postValue(Resource.Error("Ошибка соединения..."))
                else -> company.postValue(Resource.Error("Ошибка преобразования..."))
            }

        }
    }


    private fun handleDetailCompanyResponse(companyResponse: retrofit2.Response<Array<DetailResponseItem>>): Resource<Array<DetailResponseItem>> {
        if (companyResponse.isSuccessful) {
            Log.d("TAG",companyResponse.body().toString())
            companyResponse.body()?.let { resultResponse ->
                Log.d("TAG","TTTTTTt")
                this.detailCompanyResponse = resultResponse

                return Resource.Success(this.detailCompanyResponse ?: resultResponse)
            }
        }
        Log.d("TAG","TTTTTTt")
        return Resource.Error(companyResponse.message())
    }

    private fun handleCompanyResponse(companyResponse: retrofit2.Response<Array<ResponseItem>>): Resource<Array<ResponseItem>> {
        if (companyResponse.isSuccessful) {
            companyResponse.body()?.let { resultResponse ->
                this.companyResponse = resultResponse

                return Resource.Success(this.companyResponse ?: resultResponse)
            }
        }
        return Resource.Error(companyResponse.message())
    }

    private fun checkInternet(): Boolean {
        val connectivityManager = context.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= 23) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capability =
                connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capability.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capability.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                capability.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else
            connectivityManager.activeNetworkInfo?.run {
                return when (type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }
            }
        return false
    }
}