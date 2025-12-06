package com.messenger.templates.feature.presentation.di

import com.messenger.templates.domain.InitStringProvider
import com.messenger.templates.feature.presentation.InitStringProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface InitStringResourceModule {

    @Binds
    fun bindInitStringProvider(
        impl: InitStringProviderImpl
    ): InitStringProvider

}