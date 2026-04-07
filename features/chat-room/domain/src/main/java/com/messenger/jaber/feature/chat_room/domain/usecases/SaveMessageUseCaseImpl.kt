package com.messenger.jaber.feature.chat_room.domain.usecases

import com.messenger.core.essentials.entities.Id
import com.messenger.jaber.feature.chat_room.domain.SaveMessageUseCase
import com.messenger.jaber.feature.chat_room.domain.entities.Message
import com.messenger.jaber.feature.chat_room.domain.repositories.MessagesRepository
import javax.inject.Inject

class SaveMessageUseCaseImpl @Inject constructor(
    private val repository: MessagesRepository
) : SaveMessageUseCase {
    override suspend fun invoke(
        message: Message,
        chatId: Id
    ) {
        repository.saveMessage(message, chatId)
    }
}