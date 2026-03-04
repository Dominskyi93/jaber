package com.messenger.jaber.settings.domain.di

import com.messenger.jaber.settings.domain.LogoutUseCase
import com.messenger.jaber.settings.domain.usecases.LogoutUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface UseCasesModule {

    @Binds
    fun bindAuthorizedUseCase(
        impl: LogoutUseCaseImpl
    ): LogoutUseCase
}