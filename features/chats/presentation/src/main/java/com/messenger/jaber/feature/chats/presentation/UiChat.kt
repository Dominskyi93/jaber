package com.messenger.jaber.feature.chats.presentation

import com.messenger.jaber.feature.chats.domain.entities.Chat

data class UiChat(
    val origin: Chat,
    val isEnabled: Boolean
) : Chat by origin
