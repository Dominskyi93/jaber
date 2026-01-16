package com.messenger.jaber.data

import com.elveum.container.ListContainerFlow
import com.messenger.core.essentials.entities.Id
import com.messenger.jaber.feature.chats.domain.entities.Chat

interface ChatsDataRepository {
    fun getChats(): ListContainerFlow<Chat>
    suspend fun deleteChat(chatId: Id)
    suspend fun getChatById(chatId: Id): Chat
}