package com.messenger.jaber.feature.chats.domain.repositories

import com.elveum.container.ListContainerFlow
import com.messenger.core.essentials.entities.Id
import com.messenger.jaber.feature.chats.domain.entities.Chat

interface ChatsRepository {
    fun getChats(): ListContainerFlow<Chat>
    suspend fun getChatById(chatId: Id): Chat
    suspend fun deleteChat(chatId: Id)
}
