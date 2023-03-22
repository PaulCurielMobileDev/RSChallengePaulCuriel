package com.curiel_ruelas.republicserviceschallenge.ui.drivers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.curiel_ruelas.republicserviceschallenge.data.models.Driver
import com.curiel_ruelas.republicserviceschallenge.data.models.Resource
import com.curiel_ruelas.republicserviceschallenge.data.repository.DriversRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DriversViewModel @Inject constructor(private val driversRepository: DriversRepository) :
    ViewModel() {
    private val _drivers = MutableLiveData<Resource<List<Driver>>>()
    val drivers: LiveData<Resource<List<Driver>>>
        get() = _drivers

    init {
        getDrivers()
    }

    fun getDrivers() = viewModelScope.launch (Dispatchers.IO) {
        _drivers.postValue(Resource.Loading())
        driversRepository.getDrivers().let {
            _drivers.postValue(it)
        }
    }

    fun sortDrivers() = viewModelScope.launch(Dispatchers.IO) {
        _drivers.postValue(Resource.Loading())
        driversRepository.sortedDrivers().let {
            _drivers.postValue(it)
        }
    }

}