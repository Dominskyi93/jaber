package com.messenger.jaber.data.createAccount.di

import com.messenger.jaber.data.CreateAccountDataRepository
import com.messenger.jaber.data.LoginAvailabilityDataRepository
import com.messenger.jaber.data.createAccount.CreateAccountDataRepositoryImpl
import com.messenger.jaber.data.createAccount.LoginAvailabilityDataRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CreateAccountModule {

    @Binds
    fun bindCreateAccountRepository(
        impl: CreateAccountDataRepositoryImpl
    ): CreateAccountDataRepository

    @Binds
    fun bindLoginAvailabilityDataRepository(
        impl: LoginAvailabilityDataRepositoryImpl
    ): LoginAvailabilityDataRepository
}