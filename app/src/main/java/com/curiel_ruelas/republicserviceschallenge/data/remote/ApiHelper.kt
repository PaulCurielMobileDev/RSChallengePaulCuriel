package com.curiel_ruelas.republicserviceschallenge.data.remote

import com.curiel_ruelas.republicserviceschallenge.data.models.DataResponse
import retrofit2.Response

interface ApiHelper {

    suspend fun getAllData(): Response<DataResponse>

}