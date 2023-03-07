package com.curiel_ruelas.republicserviceschallenge.data.remote

import com.curiel_ruelas.republicserviceschallenge.data.models.DataResponse
import com.curiel_ruelas.republicserviceschallenge.data.models.Resource
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val apiService: ApiHelper):ResponseWrapper(),RemoteDataSource {

    override suspend fun getAllData(): Resource<DataResponse> = getResult {
        apiService.getAllData()
    }

}