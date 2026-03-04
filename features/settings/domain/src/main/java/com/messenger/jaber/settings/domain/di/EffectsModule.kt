package com.messenger.jaber.settings.domain.di

import com.messenger.jaber.settings.domain.effects.LogoutUserChoices
import com.messenger.jaber.settings.domain.effects.LogoutUserChoicesImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface EffectsModule {

    @Binds
    fun bindLogoutUserChoices(
        impl: LogoutUserChoicesImpl
    ): LogoutUserChoices
}