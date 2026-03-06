package com.messenger.jaber.data

import com.elveum.container.ListContainerFlow
import com.messenger.jaber.data.messages.entities.MessageDataEntity

interface MessagesDataRepository {
    fun getMessages(chatId: String): ListContainerFlow<MessageDataEntity>
}