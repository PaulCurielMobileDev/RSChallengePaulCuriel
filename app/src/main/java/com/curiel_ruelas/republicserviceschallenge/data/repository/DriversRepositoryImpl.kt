package com.curiel_ruelas.republicserviceschallenge.data.repository

import com.curiel_ruelas.republicserviceschallenge.data.local.LocalDataSource
import com.curiel_ruelas.republicserviceschallenge.data.models.Driver
import com.curiel_ruelas.republicserviceschallenge.data.models.Resource
import javax.inject.Inject

class DriversRepositoryImpl @Inject constructor(
    private val allDataRepository: AllDataRepository,
    private val localDataSource: LocalDataSource
):DriversRepository {

    override suspend fun getDrivers(): Resource<List<Driver>> {
        val drivers = localDataSource.getAllDrivers()
        if (drivers.isEmpty()) {
            return allDataRepository.getDrivers()

        }

        return  Resource.Success(drivers)

    }

    override suspend fun sortedDrivers(): Resource<List<Driver>> {
        val drivers = localDataSource.getSortedDrivers()
        return  Resource.Success(drivers)

    }

    override suspend fun getDriver(id: String): Driver {
        val drivers=localDataSource.getDriver(id)
        return drivers.first()
    }


}