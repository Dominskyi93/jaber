package com.messenger.jaber.feature.chat_room.domain.repositories

import com.elveum.container.ListContainerFlow
import com.messenger.core.essentials.entities.Id
import com.messenger.jaber.feature.chat_room.domain.entities.Message

interface MessagesRepository {
    fun getMessages(chatId: String): ListContainerFlow<Message>
    suspend fun deleteMessage(chatId: Id)
    suspend fun saveMessage(message: Message, chatId: Id)
}
