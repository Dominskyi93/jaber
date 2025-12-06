package com.messenger.common.android.di

import com.messenger.common.android.AndroidLogger
import com.messenger.common.android.CoreStringProviderImpl
import com.messenger.core.essentials.logger.Logger
import com.messenger.core.essentials.resources.StringProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CommonAndroidModule {

    @Binds
    fun bindLogger(logger: AndroidLogger): Logger

    @Binds
    fun bindStringProvider(
        impl: CoreStringProviderImpl
    ): StringProvider
}