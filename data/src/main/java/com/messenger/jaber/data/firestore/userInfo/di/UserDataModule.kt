package com.messenger.jaber.data.firestore.userInfo.di

import com.messenger.jaber.data.CreateUserInfoDataRepository
import com.messenger.jaber.data.firestore.userInfo.CreateUserInfoDataRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UserDataModule {

    @Binds
    fun bindUserDataRepository(
        impl: CreateUserInfoDataRepositoryImpl
    ): CreateUserInfoDataRepository

}