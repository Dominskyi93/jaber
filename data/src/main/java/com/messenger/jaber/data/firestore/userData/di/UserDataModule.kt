package com.messenger.jaber.data.firestore.userData.di

import com.messenger.jaber.data.CreateUserDataRepository
import com.messenger.jaber.data.firestore.userData.CreateUserDataRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UserDataModule {

    @Binds
    fun bindUserDataRepository(
        impl: CreateUserDataRepositoryImpl
    ): CreateUserDataRepository

}