package com.messenger.jaber.feature.chats.domain.usecases

import com.messenger.core.essentials.entities.Id
import com.messenger.jaber.feature.chats.domain.DeleteChatUseCase
import com.messenger.jaber.feature.chats.domain.effects.ChatsUserChoices
import com.messenger.jaber.feature.chats.domain.repositories.ChatsRepository
import javax.inject.Inject

internal class DeleteChatUseCaseImpl @Inject constructor(
    private val chatsRepository: ChatsRepository,
    private val userChoices: ChatsUserChoices
) : DeleteChatUseCase {
    override suspend fun invoke(chatId: Id) {
        val chat = chatsRepository.getChatById(chatId)
        val isConfirmed = userChoices.confirmChatRemoval(chat)
        if (isConfirmed) {
            chatsRepository.deleteChat(chatId)
        }
    }
}