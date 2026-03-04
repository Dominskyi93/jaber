package com.messenger.jaber.settings.presentation.di

import com.messenger.core.essentials.resources.StringProvider
import com.messenger.jaber.settings.domain.resources.SettingsStringProvider
import com.messenger.jaber.settings.presentation.resources.SettingsStringProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
@InstallIn(SingletonComponent::class)
interface SettingsStringProviderModule {

    @Binds
    @IntoMap
    @ClassKey(SettingsStringProvider::class)
    fun bindSettingsStringProviderIntoMap(
        impl: SettingsStringProviderImpl
    ): StringProvider

    @Binds
    fun bindSettingsStringProvider(
        impl: SettingsStringProviderImpl
    ): SettingsStringProvider
}