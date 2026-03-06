package com.messenger.jaber.feature.chat_room.domain

import com.elveum.container.ListContainerFlow
import com.messenger.jaber.feature.chat_room.domain.entities.Message

interface GetMessagesUseCase {
    operator fun invoke(chatId: String): ListContainerFlow<Message>
}