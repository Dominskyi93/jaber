package com.messenger.jaber.glue.init.di

import com.messenger.jaber.domain.repositories.AuthRepository
import com.messenger.jaber.domain.repositories.DateTimeRepository
import com.messenger.jaber.domain.repositories.KeyFeaturesRepository
import com.messenger.jaber.glue.init.InitAuthRepository
import com.messenger.jaber.glue.init.InitDateTimeRepository
import com.messenger.jaber.glue.init.InitKeyFeaturesRepository
import com.messenger.jaber.glue.init.mappers.KeyFeatureMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface InitModule {

    @Binds
    fun bindAuthRepository(
        impl: InitAuthRepository
    ): AuthRepository

    @Binds
    fun bindDateTimeRepository(
        impl: InitDateTimeRepository
    ): DateTimeRepository

    @Binds
    fun bindKeyFeaturesRepository(
        impl: InitKeyFeaturesRepository
    ): KeyFeaturesRepository

    @Binds
    fun bindKeyFeatureMapper(
        impl: KeyFeatureMapper.Default
    ): KeyFeatureMapper
}