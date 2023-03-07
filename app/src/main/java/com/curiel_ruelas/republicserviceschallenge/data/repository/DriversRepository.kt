package com.curiel_ruelas.republicserviceschallenge.data.repository

import com.curiel_ruelas.republicserviceschallenge.data.models.Driver
import com.curiel_ruelas.republicserviceschallenge.data.models.Resource

interface DriversRepository {
    suspend fun getDrivers(): Resource<List<Driver>>
    suspend fun sortedDrivers(): Resource<List<Driver>>
    suspend fun getDriver(id: String): Driver
}