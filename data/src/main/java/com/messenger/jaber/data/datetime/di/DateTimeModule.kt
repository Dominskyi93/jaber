package com.messenger.jaber.data.datetime.di

import com.messenger.jaber.data.DateTimeDataRepository
import com.messenger.jaber.data.datetime.DateTimeDataRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DateTimeModule {

    @Binds
    fun bindDateTimeDataRepository(
        impl: DateTimeDataRepositoryImpl
    ): DateTimeDataRepository
}