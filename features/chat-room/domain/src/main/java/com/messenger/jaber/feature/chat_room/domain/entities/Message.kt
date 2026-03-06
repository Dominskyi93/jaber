package com.messenger.jaber.feature.chat_room.domain.entities

interface Message {
    val id: String
    val isRead: Boolean
    val text: String
    val senderId: String
    val timestamp: Long
    val isMyMessage: Boolean

    data class Default(
        override val id: String = "",
        override val text: String = "",
        override val isRead: Boolean = false,
        override val senderId: String = "",
        override val timestamp: Long = 0L,
        override val isMyMessage: Boolean = false
    ) : Message
}
