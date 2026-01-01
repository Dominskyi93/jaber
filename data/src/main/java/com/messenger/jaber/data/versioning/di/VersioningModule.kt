package com.messenger.jaber.data.versioning.di

import com.messenger.jaber.data.VersioningRepository
import com.messenger.jaber.data.versioning.VersioningRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface VersioningModule {

    @Binds
    fun bindVersioningRepository(
        impl: VersioningRepositoryImpl
    ): VersioningRepository
}