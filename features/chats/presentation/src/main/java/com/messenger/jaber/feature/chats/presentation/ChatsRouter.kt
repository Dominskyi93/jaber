package com.messenger.jaber.feature.chats.presentation

interface ChatsRouter {
    fun navigateUp()
    fun navigateToChat(chatId: String)
}