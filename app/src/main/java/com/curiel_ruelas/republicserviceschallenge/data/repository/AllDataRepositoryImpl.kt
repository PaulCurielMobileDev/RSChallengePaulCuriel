package com.curiel_ruelas.republicserviceschallenge.data.repository

import com.curiel_ruelas.republicserviceschallenge.data.local.LocalDataSource
import com.curiel_ruelas.republicserviceschallenge.data.models.Driver
import com.curiel_ruelas.republicserviceschallenge.data.models.Resource
import com.curiel_ruelas.republicserviceschallenge.data.remote.RemoteDataSource
import javax.inject.Inject

class AllDataRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : AllDataRepository {

    override suspend fun getDrivers(): Resource<List<Driver>> {
        return when (val ans = remoteDataSource.getAllData()) {
            is Resource.Success -> {
                val drivers = ans.data.drivers
                val routes = ans.data.routes
                localDataSource.insertAllDrivers(drivers)
                localDataSource.insertAllRoutes(routes)
                Resource.Success(drivers)
            }
            is Resource.Error -> {
                Resource.Error(msg = ans.msg)
            }
            else -> {
                Resource.Loading()
            }
        }
    }
}