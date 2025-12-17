package com.messenger.common.android.di

import com.messenger.common.android.AndroidExceptionHandler
import com.messenger.common.android.AndroidLogger
import com.messenger.common.android.CoreStringProviderImpl
import com.messenger.core.essentials.exceptions.handler.ExceptionHandler
import com.messenger.core.essentials.exceptions.mapper.DefaultExceptionMessageMapper
import com.messenger.core.essentials.exceptions.mapper.ExceptionToMessageMapper
import com.messenger.core.essentials.logger.Logger
import com.messenger.core.essentials.resources.CoreStringProvider
import com.messenger.core.essentials.resources.StringProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
@InstallIn(SingletonComponent::class)
interface CommonAndroidModule {

    @Binds
    fun bindLogger(logger: AndroidLogger): Logger

    @Binds
    @IntoMap
    @ClassKey(CoreStringProvider::class)
    fun bindCoreStringProvider(
        impl: CoreStringProviderImpl
    ): StringProvider

    @Binds
    fun bindExceptionToMessageMapper(
        impl: DefaultExceptionMessageMapper
    ): ExceptionToMessageMapper

}