package com.curiel_ruelas.republicserviceschallenge.data.remote

import com.curiel_ruelas.republicserviceschallenge.BuildConfig
import com.curiel_ruelas.republicserviceschallenge.utils.Config
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkConfiguration {

    fun interceptorDebug(): Interceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return loggingInterceptor
    }

    fun httpClient() = OkHttpClient.Builder().build()

    fun debugHttpClient(interceptor: Interceptor) = OkHttpClient.Builder().addInterceptor(interceptor).build()

    fun createRetrofit(okHttpClientDebug:OkHttpClient,okHttpClientAuth:OkHttpClient):Retrofit{
        val url= Config.BASE_URL
        val gson= GsonBuilder().create()

        return  Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(url)
            .client(if (BuildConfig.DEBUG) okHttpClientDebug else okHttpClientAuth)
            .build()
    }
}