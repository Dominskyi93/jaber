package com.messenger.jaber.feature.chats.domain.resources

import com.messenger.core.essentials.resources.StringProvider
import com.messenger.jaber.feature.chats.domain.entities.Chat

interface ChatsStringProvider : StringProvider {
    val confirmDeleteDialogTitle: String
    fun confirmDeleteDialogMessage(chat: Chat): String
}