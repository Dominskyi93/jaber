package com.messenger.jaber.glue.chats.di

import com.messenger.jaber.feature.chats.domain.repositories.ChatsRepository
import com.messenger.jaber.glue.chats.ChatsGetChatsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ChatsModule {

    @Binds
    fun bindChatsRepository(
        impl: ChatsGetChatsRepository
    ): ChatsRepository

}