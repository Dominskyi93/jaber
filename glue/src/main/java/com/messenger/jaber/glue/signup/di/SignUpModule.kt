package com.messenger.jaber.glue.signup.di

import com.messenger.jaber.features.signup.domain.repositories.CreateAccountRepository
import com.messenger.jaber.features.signup.domain.repositories.LoginAvailabilityRepository
import com.messenger.jaber.glue.signup.SignUpCreateAccountRepository
import com.messenger.jaber.glue.signup.SignUpLoginAvailabilityRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface SignUpModule {

    @Binds
    fun bindCreateAccountRepository(
        impl: SignUpCreateAccountRepository
    ): CreateAccountRepository

    @Binds
    fun bindLoginAvailabilityRepository(
        impl: SignUpLoginAvailabilityRepository
    ): LoginAvailabilityRepository

}