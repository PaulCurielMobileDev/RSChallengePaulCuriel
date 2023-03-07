package com.curiel_ruelas.republicserviceschallenge.ui.routes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.curiel_ruelas.republicserviceschallenge.data.models.Driver
import com.curiel_ruelas.republicserviceschallenge.data.models.Resource
import com.curiel_ruelas.republicserviceschallenge.data.models.Route
import com.curiel_ruelas.republicserviceschallenge.data.repository.DriversRepository
import com.curiel_ruelas.republicserviceschallenge.data.repository.RoutesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoutesViewModel @Inject constructor(
    private val routesRepository: RoutesRepository,
    private val driversRepository: DriversRepository
) : ViewModel() {

    private var _routes = MutableLiveData<Resource<List<Route>>>()
    val routes: LiveData<Resource<List<Route>>> get() = _routes

    private var _driver = MutableLiveData<Driver>()
    val driver: LiveData<Driver> get() = _driver


    fun getRoutes(id: String) = viewModelScope.launch(Dispatchers.IO) {
        _routes.postValue(Resource.Loading())

        val routesDeferref = async {
            routesRepository.getRoutesFilters(id)
        }
        val routes = routesDeferref.await()
        if (routes.isEmpty()) {
            val lastI = async { routesRepository.getLastI() }
            _routes.postValue(Resource.Success(lastI.await()))
        } else {
            _routes.postValue(Resource.Success(routes))
        }
        val driver = async { driversRepository.getDriver(id) }
        _driver.postValue(driver.await())
    }
}