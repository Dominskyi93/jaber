package com.messenger.jaber.features.signup.domain.di

import com.messenger.jaber.features.signup.domain.validators.NewAccountValidator
import com.messenger.jaber.features.signup.domain.validators.NewAccountValidatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface ValidatorModule {

    @Binds
    fun bindSignUpUseCase(impl: NewAccountValidatorImpl): NewAccountValidator

}