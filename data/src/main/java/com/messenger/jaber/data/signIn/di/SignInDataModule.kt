package com.messenger.jaber.data.signIn.di

import com.messenger.jaber.data.SignInDataRepository
import com.messenger.jaber.data.signIn.SignInDataRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface SignInDataModule {

    @Binds
    fun bindSignInDataRepository(
        impl: SignInDataRepositoryImpl
    ): SignInDataRepository

}