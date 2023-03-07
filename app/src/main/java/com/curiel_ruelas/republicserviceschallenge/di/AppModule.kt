package com.curiel_ruelas.republicserviceschallenge.di

import android.content.Context
import com.curiel_ruelas.republicserviceschallenge.data.local.AppDatabase
import com.curiel_ruelas.republicserviceschallenge.data.local.LocalDataSource
import com.curiel_ruelas.republicserviceschallenge.data.local.LocalDataSourceImpl
import com.curiel_ruelas.republicserviceschallenge.data.remote.*
import com.curiel_ruelas.republicserviceschallenge.data.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
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
    fun provideNetworkConfiguration(): NetworkConfiguration = NetworkConfiguration()

    @Provides
    fun provideDebugInterceptor(networkConfiguration: NetworkConfiguration): Interceptor =
        networkConfiguration.interceptorDebug()

    @AuthInterceptorOkHttpClient
    @Provides
    fun provideAuthInterceptorOkHttpClient(networkConfiguration: NetworkConfiguration): OkHttpClient {
        return networkConfiguration.httpClient()
    }

    @DebugInterceptorOkHttpClient
    @Provides
    fun provideDebugInterceptorOkHttpClient(
        networkConfiguration: NetworkConfiguration,
        interceptor: Interceptor
    ): OkHttpClient {
        return networkConfiguration.debugHttpClient(interceptor)
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        @AuthInterceptorOkHttpClient okHttpClientAuth: OkHttpClient,
        @DebugInterceptorOkHttpClient okHttpClientDebug: OkHttpClient,
        networkConfiguration: NetworkConfiguration
    ): Retrofit = networkConfiguration.createRetrofit(okHttpClientDebug, okHttpClientAuth)

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper


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


    @Provides
    fun provideAllDataRepository(allDataRepository: AllDataRepositoryImpl): AllDataRepository =
        allDataRepository

    @Provides
    fun provideDriversRepository(driversRepository: DriversRepositoryImpl): DriversRepository =
        driversRepository

    @Provides
    fun provideRoutesRepository(routesRepository: RoutesRepositoryImpl): RoutesRepository =
        routesRepository

    @Provides
    fun provideLocalDataSource(localDataSource: LocalDataSourceImpl): LocalDataSource =
        localDataSource

}