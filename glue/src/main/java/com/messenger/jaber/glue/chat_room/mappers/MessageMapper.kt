package com.messenger.jaber.glue.chat_room.mappers

import com.messenger.jaber.data.messages.entities.MessageDataEntity
import com.messenger.jaber.feature.chat_room.domain.entities.Message
import javax.inject.Inject

interface MessageMapper {
    fun mapToMessage(messageDataEntity: MessageDataEntity): Message

    class Default @Inject constructor() : MessageMapper {
        override fun mapToMessage(messageDataEntity: MessageDataEntity): Message =
            with(messageDataEntity) {
                return Message.Default(
                    id = id,
                    text = text,
                    isRead = isRead,
                    senderId = senderId,
                    timestamp = timestamp,
                    isMyMessage = isMyMessage
                )
            }
    }
}