package com.messenger.jaber.data.accounts.di

import com.messenger.jaber.data.AccountsDataRepository
import com.messenger.jaber.data.accounts.AccountsDataRepositoryImpl
import com.messenger.jaber.data.accounts.remote.AccountsApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
internal interface AccountsModule {

    @Binds
    fun bindAccountsDataRepository(
        impl: AccountsDataRepositoryImpl
    ): AccountsDataRepository

    @Provides
    fun provideAccountsApi(retrofit: Retrofit): AccountsApi {
        return retrofit.create()
    }
}