package com.curiel_ruelas.republicserviceschallenge.data.repository

import com.curiel_ruelas.republicserviceschallenge.data.models.Route

interface RoutesRepository {
    suspend fun getRoutes(): List<Route>
    suspend fun getRoute(id: Int): Route
    suspend fun getRoutesFilters(id: String): List<Route>
    suspend fun getLastI(): List<Route>
}