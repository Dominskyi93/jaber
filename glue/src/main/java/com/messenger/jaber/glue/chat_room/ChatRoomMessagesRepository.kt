package com.messenger.jaber.glue.chat_room

import com.elveum.container.ListContainerFlow
import com.elveum.container.containerMap
import com.messenger.core.essentials.entities.Id
import com.messenger.jaber.data.MessagesDataRepository
import com.messenger.jaber.feature.chat_room.domain.entities.Message
import com.messenger.jaber.feature.chat_room.domain.repositories.MessagesRepository
import com.messenger.jaber.glue.chat_room.mappers.MessageMapper
import javax.inject.Inject

internal class ChatRoomMessagesRepository @Inject constructor(
    private val messagesDataRepository: MessagesDataRepository,
    private val messageMapper: MessageMapper
) : MessagesRepository {

    override fun getMessages(chatId: String): ListContainerFlow<Message> {
        return messagesDataRepository.getMessages(chatId)
            .containerMap { list ->
                list.map(messageMapper::mapToMessage)
            }
    }

    override suspend fun deleteMessage(chatId: Id) {
        TODO("Not yet implemented")
    }

    override suspend fun saveMessage(
        message: Message,
        chatId: Id
    ) {
        messagesDataRepository.saveMessage(
            messageMapper.mapToMessageDataEntity(message), chatId.value
        )
    }

}