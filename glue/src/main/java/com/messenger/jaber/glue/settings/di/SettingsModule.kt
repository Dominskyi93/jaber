package com.messenger.jaber.glue.settings.di

import com.messenger.jaber.glue.settings.SettingsClearLocalTokenRepository
import com.messenger.jaber.glue.settings.SettingsLogoutRepository
import com.messenger.jaber.settings.domain.repositories.ClearLocalTokenRepository
import com.messenger.jaber.settings.domain.repositories.LogoutRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface SettingsModule {

    @Binds
    fun bindLogoutRepository(
        impl: SettingsLogoutRepository
    ): LogoutRepository

    @Binds
    fun bindClearLocalTokenRepository(
        impl: SettingsClearLocalTokenRepository
    ): ClearLocalTokenRepository

}