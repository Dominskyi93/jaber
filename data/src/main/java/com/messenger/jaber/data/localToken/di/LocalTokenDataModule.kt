package com.messenger.jaber.data.localToken.di

import com.messenger.jaber.data.LocalTokenDataRepository
import com.messenger.jaber.data.localToken.LocalTokenDataRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface LocalTokenDataModule {

    @Binds
    fun bindLocalTokenDataRepository(
        impl: LocalTokenDataRepositoryImpl
    ): LocalTokenDataRepository
}