package com.messenger.jaber.glue.signin.di

import com.messenger.jaber.glue.signin.SignInFeatureRepository
import com.messenger.jaber.glue.signin.SignInLocalTokenRepository
import com.messenger.jaber.signin.domain.repositories.LocalTokenRepository
import com.messenger.jaber.signin.domain.repositories.SignInRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface SignInModule {

    @Binds
    fun bindSignInRepository(
        impl: SignInFeatureRepository
    ): SignInRepository

    @Binds
    fun bindLocalTokenRepository(
        impl: SignInLocalTokenRepository
    ): LocalTokenRepository
}