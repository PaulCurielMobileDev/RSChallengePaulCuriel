package com.curiel_ruelas.republicserviceschallenge.data.repository

import com.curiel_ruelas.republicserviceschallenge.data.local.AllDao
import com.curiel_ruelas.republicserviceschallenge.data.models.Driver
import com.curiel_ruelas.republicserviceschallenge.data.models.Resource
import com.curiel_ruelas.republicserviceschallenge.data.remote.RemoteDataSource
import javax.inject.Inject

interface AllDataRepository {

    suspend fun getDrivers(): Resource<List<Driver>>
}