package com.messenger.common.android.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

@Module
@InstallIn(SingletonComponent::class)
internal object CoroutineScopeModule {

    @Provides
    fun provideScope(): CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
}