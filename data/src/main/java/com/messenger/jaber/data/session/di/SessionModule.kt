package com.messenger.jaber.data.session.di

import com.messenger.jaber.data.SessionProvider
import com.messenger.jaber.data.session.SessionManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface SessionModule {

    @Binds
    fun bindSessionProvider(
        impl: SessionManagerImpl
    ): SessionProvider

}