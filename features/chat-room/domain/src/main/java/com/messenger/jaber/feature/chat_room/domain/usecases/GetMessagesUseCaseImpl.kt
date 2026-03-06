package com.messenger.jaber.feature.chat_room.domain.usecases

import com.elveum.container.ListContainerFlow
import com.messenger.jaber.feature.chat_room.domain.GetMessagesUseCase
import com.messenger.jaber.feature.chat_room.domain.entities.Message
import com.messenger.jaber.feature.chat_room.domain.repositories.MessagesRepository
import javax.inject.Inject

class GetMessagesUseCaseImpl @Inject constructor(
    private val repository: MessagesRepository
) : GetMessagesUseCase {
    override fun invoke(chatId: String): ListContainerFlow<Message> {
        return repository.getMessages(chatId)
    }
}