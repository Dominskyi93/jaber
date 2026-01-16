package com.messenger.jaber.feature.chats.domain.effects

import com.messenger.jaber.feature.chats.domain.entities.Chat

interface ChatsUserChoices {
    suspend fun confirmChatRemoval(chat: Chat): Boolean

}
