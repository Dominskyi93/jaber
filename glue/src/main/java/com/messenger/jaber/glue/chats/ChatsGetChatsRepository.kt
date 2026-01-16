package com.messenger.jaber.glue.chats

import com.elveum.container.ListContainerFlow
import com.messenger.core.essentials.entities.Id
import com.messenger.jaber.data.ChatsDataRepository
import com.messenger.jaber.feature.chats.domain.entities.Chat
import com.messenger.jaber.feature.chats.domain.repositories.ChatsRepository
import javax.inject.Inject

class ChatsGetChatsRepository @Inject constructor(
    private val getChatsDataRepository: ChatsDataRepository
) : ChatsRepository {

    override fun getChats(): ListContainerFlow<Chat> {
        return getChatsDataRepository.getChats()
    }

    override suspend fun getChatById(chatId: Id): Chat {
        return getChatsDataRepository.getChatById(chatId)
    }

    override suspend fun deleteChat(chatId: Id) {
        getChatsDataRepository.deleteChat(chatId)
    }

}