package com.messenger.jaber.features.signup.domain.di

import com.messenger.jaber.features.signup.domain.SignUpUseCase
import com.messenger.jaber.features.signup.domain.ValidateAccountUseCase
import com.messenger.jaber.features.signup.domain.usecases.SignUpUseCaseImpl
import com.messenger.jaber.features.signup.domain.usecases.ValidateAccountUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface UseCasesModule {

    @Binds
    fun bindSignUpUseCase(impl: SignUpUseCaseImpl): SignUpUseCase

    @Binds
    fun bindValidateAccountUseCase(impl: ValidateAccountUseCaseImpl): ValidateAccountUseCase

}