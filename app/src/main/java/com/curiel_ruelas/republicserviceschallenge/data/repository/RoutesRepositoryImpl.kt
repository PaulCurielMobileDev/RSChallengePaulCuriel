package com.curiel_ruelas.republicserviceschallenge.data.repository

import com.curiel_ruelas.republicserviceschallenge.data.local.AllDao
import com.curiel_ruelas.republicserviceschallenge.data.models.Route
import javax.inject.Inject

class RoutesRepositoryImpl @Inject constructor(private val localDataSource: AllDao) :
    RoutesRepository {
    override suspend fun getRoutes() = localDataSource.getAllRoutes()
    override suspend fun getRoute(id: Int) = localDataSource.getRoute(id).first()
    override suspend fun getRoutesFilters(id: String) = localDataSource.getRoutesFilters(id)
    override suspend fun getLastI() = localDataSource.getLastI()
}