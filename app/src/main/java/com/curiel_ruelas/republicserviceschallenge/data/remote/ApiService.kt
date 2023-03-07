package com.curiel_ruelas.republicserviceschallenge.data.remote

import com.curiel_ruelas.republicserviceschallenge.data.models.DataResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("data")
    suspend fun getAllData():Response<DataResponse>

}