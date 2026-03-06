package com.messenger.jaber.feature.chats.presentation.di

import com.messenger.core.essentials.resources.StringProvider
import com.messenger.jaber.feature.chats.domain.resources.ChatsStringProvider
import com.messenger.jaber.feature.chats.presentation.resources.ChatsStringProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
@InstallIn(SingletonComponent::class)
interface ChatsStringProviderModule {

    @Binds
    @IntoMap
    @ClassKey(ChatsStringProvider::class)
    fun bindChatsStringProviderIntoMap(
        impl: ChatsStringProviderImpl
    ): StringProvider

    @Binds
    fun bindChatsStringProvider(
        impl: ChatsStringProviderImpl
    ): ChatsStringProvider
}