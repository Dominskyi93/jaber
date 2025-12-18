package com.messenger.jaber.feature.presentation.di

import com.messenger.core.essentials.resources.StringProvider
import com.messenger.jaber.domain.resources.InitStringProvider
import com.messenger.jaber.feature.presentation.InitStringProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
@InstallIn(SingletonComponent::class)
interface InitStringResourceModule {

    @Binds
    @IntoMap
    @ClassKey(InitStringProvider::class)
    fun bindInitStringProvider(
        impl: InitStringProviderImpl
    ): StringProvider

}