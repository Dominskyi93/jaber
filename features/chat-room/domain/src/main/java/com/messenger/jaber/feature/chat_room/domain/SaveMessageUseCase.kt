package com.messenger.jaber.feature.chat_room.domain

import com.messenger.core.essentials.entities.Id
import com.messenger.jaber.feature.chat_room.domain.entities.Message

interface SaveMessageUseCase {
    suspend operator fun invoke(message: Message, chatId: Id)
}