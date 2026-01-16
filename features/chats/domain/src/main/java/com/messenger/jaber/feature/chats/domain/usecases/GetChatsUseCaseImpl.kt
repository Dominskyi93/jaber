package com.messenger.jaber.feature.chats.domain.usecases

import com.elveum.container.ListContainerFlow
import com.messenger.jaber.feature.chats.domain.GetChatsUseCase
import com.messenger.jaber.feature.chats.domain.entities.Chat
import com.messenger.jaber.feature.chats.domain.repositories.ChatsRepository
import javax.inject.Inject

 class GetChatsUseCaseImpl @Inject constructor(
    private val repository: ChatsRepository
) : GetChatsUseCase {
    override fun invoke(): ListContainerFlow<Chat> {
        return repository.getChats()
    }
}