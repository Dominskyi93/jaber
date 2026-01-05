package com.messenger.jaber.core.data.network.di

import com.messenger.jaber.core.data.network.NetworkConfig
import com.messenger.jaber.core.data.network.clients.createDefaultOkHttpClient
import com.messenger.jaber.core.data.network.clients.createDefaultRetrofit
import com.messenger.jaber.core.data.network.converter.createDefaultJson
import com.messenger.jaber.core.data.network.interceptors.AuthInterceptor
import com.messenger.jaber.core.data.network.interceptors.createHttpLoggingInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(
        networkConfig: NetworkConfig,
        okHttpClient: OkHttpClient,
        json: Json
    ): Retrofit {
        return createDefaultRetrofit(
            baseUrl = networkConfig.baseUrl,
            client = okHttpClient,
            json = json
        )
    }

    @Provides
    @Singleton
    fun provideOkHttp(
        networkConfig: NetworkConfig,
        authInterceptor: AuthInterceptor
    ): OkHttpClient {
        return createDefaultOkHttpClient(
            timeout = networkConfig.timeout,
            interceptors = listOf(
                createHttpLoggingInterceptor(networkConfig.isDebug),
                authInterceptor
            )
        )
    }

    @Provides
    @Singleton
    fun provideDefaultJson(networkConfig: NetworkConfig): Json {
        return createDefaultJson(networkConfig.isDebug)
    }
}