package com.curiel_ruelas.republicserviceschallenge.data.local

import com.curiel_ruelas.republicserviceschallenge.data.models.Driver
import com.curiel_ruelas.republicserviceschallenge.data.models.Route

interface LocalDataSource {

    fun getAllDrivers() : List<Driver>

    fun getSortedDrivers() : List<Driver>

    fun getDriver(id:String) : List<Driver>

    suspend fun insertAllDrivers(drivers: List<Driver>)

    fun getAllRoutes() : List<Route>

    fun getRoute(id:Int) : List<Route>

    fun getRoutesFilters(id: String): List<Route>

    fun getLastI() : List<Route>

    suspend fun insertAllRoutes(drivers: List<Route>)

}