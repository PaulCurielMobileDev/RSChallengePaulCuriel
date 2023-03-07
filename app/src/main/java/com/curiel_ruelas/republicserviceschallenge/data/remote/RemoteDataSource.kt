package com.curiel_ruelas.republicserviceschallenge.data.remote

import com.curiel_ruelas.republicserviceschallenge.data.models.DataResponse
import com.curiel_ruelas.republicserviceschallenge.data.models.Resource
import javax.inject.Inject

interface RemoteDataSource {

    suspend fun getAllData(): Resource<DataResponse>

}