package com.messenger.jaber.feature.chats.presentation

import com.messenger.core.essentials.entities.Id

sealed class ChatsAction {
    data class DeleteChat(val chatId: Id) : ChatsAction()
    data class GoToChat(val chatId: Id) : ChatsAction()
}