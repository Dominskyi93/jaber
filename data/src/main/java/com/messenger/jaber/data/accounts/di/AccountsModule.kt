package com.messenger.jaber.data.accounts.di

import com.messenger.jaber.data.AccountsDataRepository
import com.messenger.jaber.data.accounts.AccountsDataRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface AccountsModule {

    @Binds
    fun bindAccountsDataRepository(
        impl: AccountsDataRepositoryImpl
    ): AccountsDataRepository

}