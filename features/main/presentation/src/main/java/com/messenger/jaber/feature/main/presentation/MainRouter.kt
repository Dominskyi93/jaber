package com.messenger.jaber.feature.main.presentation

import com.messenger.core.essentials.entities.Id

interface MainRouter {
    fun navigateUp()
    fun navigateToChat(chatId: Id)
}