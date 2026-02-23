package com.messenger.jaber.glue.chats

import com.elveum.container.ListContainerFlow
import com.elveum.container.containerMap
import com.messenger.core.essentials.entities.Id
import com.messenger.jaber.data.RoomsDataRepository
import com.messenger.jaber.feature.chats.domain.entities.Chat
import com.messenger.jaber.feature.chats.domain.repositories.ChatsRepository
import com.messenger.jaber.glue.chats.mappers.ChatMapper
import javax.inject.Inject

internal class ChatsGetChatsRepository @Inject constructor(
    private val roomsDataRepository: RoomsDataRepository,
    private val chatMapper: ChatMapper
) : ChatsRepository {

    override fun getChats(): ListContainerFlow<Chat> {
        return roomsDataRepository.getRooms()
            .containerMap { list ->
                list.map(chatMapper::mapToChat)
            }
    }

    override suspend fun getChatById(chatId: Id): Chat {
        TODO("Not yet implemented")
    }

    override suspend fun deleteChat(chatId: Id) {
        TODO("Not yet implemented")
    }


}