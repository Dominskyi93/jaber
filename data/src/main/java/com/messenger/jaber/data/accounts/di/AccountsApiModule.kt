package com.messenger.jaber.data.accounts.di

import com.messenger.jaber.data.accounts.remote.AccountsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object AccountsApiModule {

    @Provides
    @Singleton
    fun provideAccountsApi(retrofit: Retrofit): AccountsApi {
        return retrofit.create()
    }
}