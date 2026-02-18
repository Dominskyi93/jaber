package com.messenger.jaber.feature.chats.domain.entities

import com.messenger.core.essentials.entities.Id
import com.messenger.core.essentials.entities.ImageSource

interface Chat {
    val id: Id
    val title: String
    val userIds: List<Id>
    val imageSource: ImageSource
    val lastMessage: String?
    val unreadMessageCount: Int

    data class Default(
        override val id: Id,
        override val title: String,
        override val userIds: List<Id> = emptyList(),
        override val imageSource: ImageSource = ImageSource.Empty,
        override val lastMessage: String? = null,
        override val unreadMessageCount: Int = 0
    ) : Chat
}

val Chat.hasUnreadMessages: Boolean get() = unreadMessageCount > 0