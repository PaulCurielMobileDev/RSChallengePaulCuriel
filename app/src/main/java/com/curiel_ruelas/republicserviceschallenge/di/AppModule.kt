package com.curiel_ruelas.republicserviceschallenge.di

import android.content.Context
import com.curiel_ruelas.republicserviceschallenge.BuildConfig
import com.curiel_ruelas.republicserviceschallenge.data.local.AllDao
import com.curiel_ruelas.republicserviceschallenge.data.local.AppDatabase
import com.curiel_ruelas.republicserviceschallenge.data.remote.*
import com.curiel_ruelas.republicserviceschallenge.data.repository.*
import com.curiel_ruelas.republicserviceschallenge.utils.Config
import com.curiel_ruelas.republicserviceschallenge.utils.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthInterceptorOkHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DebugInterceptorOkHttpClient

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseUrl() = Config.BASE_URL

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideDebugInterceptor(): Interceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return loggingInterceptor
    }

    @AuthInterceptorOkHttpClient
    @Provides
    fun provideAuthInterceptorOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @DebugInterceptorOkHttpClient
    @Provides
    fun provideDebugInterceptorOkHttpClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        gson: Gson,
        @AuthInterceptorOkHttpClient okHttpClientAuth: OkHttpClient,
        @DebugInterceptorOkHttpClient okHttpClientDebug: OkHttpClient,
        BASE_URL: String
    ): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl(BASE_URL)
        .client(if (BuildConfig.DEBUG) okHttpClientDebug else okHttpClientAuth)
        .build()

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper


    @Singleton
    @Provides
    fun provideRemoteDataSource(remoteDataSource: RemoteDataSourceImpl): RemoteDataSource =
        remoteDataSource

    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext appContext: Context) =
        AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideAllDao(db: AppDatabase) = db.allDao()

    @Singleton
    @Provides
    fun provideAllDataRepository(allDataRepository: AllDataRepositoryImpl): AllDataRepository =
        allDataRepository

    @Provides
    fun provideDriversRepository(driversRepository: DriversRepositoryImpl): DriversRepository =
        driversRepository

    @Provides
    fun provedeRoutesRepository(routesRepository: RoutesRepositoryImpl): RoutesRepository =
        routesRepository

}