package com.curiel_ruelas.republicserviceschallenge.data.remote

import android.util.Log
import com.curiel_ruelas.republicserviceschallenge.data.models.Resource
import retrofit2.Response

abstract class ResponseWrapper {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Resource.Success(body)
            }
            return Resource.Error(" ${response.code()} ${response.message()}", data = null)
        } catch (e: Exception) {
            return Resource.Error(msg = e.message ?: e.toString(), data = null)
        }
    }

    private fun <T> error(message: String): Resource<T> {
        Log.e("remoteDataSource", message)
        return Resource.Error("Network call has failed for a following reason: $message", null)
    }
}