package com.messenger.jaber.domain.di

import com.messenger.jaber.domain.IsAuthorizedUseCase
import com.messenger.jaber.domain.ShowRandomKeyFeatureUseCase
import com.messenger.jaber.domain.usecases.IsAuthorizedUseCaseImpl
import com.messenger.jaber.domain.usecases.ShowRandomKeyFeatureUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface UseCasesModule {

    @Binds
    fun bindAuthorizedUseCase(
        impl: IsAuthorizedUseCaseImpl
    ): IsAuthorizedUseCase

    @Binds
    fun bindShowRandomKeyFeatureUseCase(
        impl: ShowRandomKeyFeatureUseCaseImpl
    ): ShowRandomKeyFeatureUseCase
}