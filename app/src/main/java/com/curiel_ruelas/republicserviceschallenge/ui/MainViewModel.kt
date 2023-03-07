package com.curiel_ruelas.republicserviceschallenge.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.curiel_ruelas.republicserviceschallenge.data.models.Resource
import com.curiel_ruelas.republicserviceschallenge.data.repository.AllDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val allDataRepository: AllDataRepository) :
    ViewModel() {


    var isReady = MutableLiveData<Resource<Boolean>>()

    init {
        getAllInfoFromWeb()
    }

    private fun getAllInfoFromWeb() = viewModelScope.launch(Dispatchers.IO)
    {
        isReady.postValue(Resource.Loading())
        when (val ans = allDataRepository.getDrivers()) {
            is Resource.Success -> isReady.postValue(Resource.Success(true))
            is Resource.Loading -> isReady.postValue(Resource.Error("Cant read data"))
            is Resource.Error -> isReady.postValue(Resource.Error(ans.msg))

        }

    }
}