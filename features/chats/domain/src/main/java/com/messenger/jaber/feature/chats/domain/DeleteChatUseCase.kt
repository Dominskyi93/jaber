package com.messenger.jaber.feature.chats.domain

import com.messenger.core.essentials.entities.Id

interface DeleteChatUseCase {
    suspend operator fun invoke(chatId: Id)
}