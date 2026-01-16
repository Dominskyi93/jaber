package com.messenger.jaber.feature.chats.domain.di

import com.messenger.jaber.feature.chats.domain.effects.ChatsUserChoices
import com.messenger.jaber.feature.chats.domain.effects.ChatsUserChoicesImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface EffectsModule {

    @Binds
    fun bindChatUserChoices(
        impl: ChatsUserChoicesImpl
    ): ChatsUserChoices

}