package com.messenger.jaber.glue.signup.di

import com.messenger.jaber.features.signup.domain.repositories.CreateAccountRepository
import com.messenger.jaber.features.signup.domain.repositories.CreateUserRepository
import com.messenger.jaber.glue.signup.SignUpCreateAccountRepository
import com.messenger.jaber.glue.signup.SignUpCreateUserDataRepository
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
    fun bindCreateUserDataRepository(
        impl: SignUpCreateUserDataRepository
    ): CreateUserRepository

}