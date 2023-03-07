package com.curiel_ruelas.republicserviceschallenge.data.local

import com.curiel_ruelas.republicserviceschallenge.data.models.Driver
import com.curiel_ruelas.republicserviceschallenge.data.models.Route
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val dao: AllDao) : LocalDataSource {
    override fun getAllDrivers() = dao.getAllDrivers()

    override fun getSortedDrivers() = dao.getSortedDrivers()

    override fun getDriver(id: String) = dao.getDriver(id)

    override fun insertAllDrivers(drivers: List<Driver>) = dao.insertAllDrivers(drivers)

    override fun getAllRoutes() = dao.getAllRoutes()

    override fun getRoute(id: Int) = dao.getRoute(id)

    override fun getRoutesFilters(id: String) = dao.getRoutesFilters(id)

    override fun getLastI() = dao.getLastI()

    override fun insertAllRoutes(drivers: List<Route>) = dao.insertAllRoutes(drivers)
}