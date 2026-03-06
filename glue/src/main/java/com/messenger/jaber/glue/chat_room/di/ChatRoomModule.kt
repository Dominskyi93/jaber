package com.messenger.jaber.glue.chat_room.di

import com.messenger.jaber.feature.chat_room.domain.repositories.MessagesRepository
import com.messenger.jaber.glue.chat_room.ChatRoomMessagesRepository
import com.messenger.jaber.glue.chat_room.mappers.MessageMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface ChatRoomModule {

    @Binds
    fun bindMessagesRepository(
        impl: ChatRoomMessagesRepository
    ): MessagesRepository

    @Binds
    fun bindMessagesMapper(
        impl: MessageMapper.Default
    ): MessageMapper

}