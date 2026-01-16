package com.messenger.jaber.feature.chats.presentation

import com.messenger.core.essentials.entities.Id

interface ChatsRouter {
    fun navigateUp()
    fun navigateToChat(chatId: Id)
}