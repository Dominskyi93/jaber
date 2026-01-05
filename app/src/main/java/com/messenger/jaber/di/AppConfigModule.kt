package com.messenger.jaber.di

import com.messenger.jaber.core.data.network.NetworkConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppConfigModule {

    @Provides
    @Singleton
    fun providesNetworkConfig() = NetworkConfig(
//        isDebug = BuildConfig.isDebug
    )
}