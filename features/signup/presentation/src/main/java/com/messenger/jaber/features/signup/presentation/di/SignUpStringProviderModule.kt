package com.messenger.jaber.features.signup.presentation.di

import com.messenger.core.essentials.resources.StringProvider
import com.messenger.jaber.features.signup.domain.resources.SignUpStringProvider
import com.messenger.jaber.features.signup.presentation.resources.SignUpStringProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
@InstallIn(SingletonComponent::class)
interface SignUpStringProviderModule {

    @Binds
    @IntoMap
    @ClassKey(SignUpStringProvider::class)
    fun bindSignUpStringProviderIntoMap(
        impl: SignUpStringProviderImpl
    ): StringProvider

    @Binds
    fun bindSignUpStringProvider(
        impl: SignUpStringProviderImpl
    ): SignUpStringProvider
}