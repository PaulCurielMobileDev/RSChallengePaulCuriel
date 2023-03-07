package com.curiel_ruelas.republicserviceschallenge.data.remote

import com.curiel_ruelas.republicserviceschallenge.data.models.DataResponse
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {
    override suspend fun getAllData(): Response<DataResponse> = apiService.getAllData()
}