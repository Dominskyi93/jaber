package com.messenger.jaber.data.messages.entities

interface MessageDataEntity {
    val id: String
    val isRead: Boolean
    val isMyMessage: Boolean
    val text: String
    val senderId: String
    val timestamp: Long

    data class Default(
        override val id: String = "",
        override val text: String = "",
        override val isRead: Boolean = false,
        override val senderId: String = "",
        override val timestamp: Long = 0L,
        override val isMyMessage: Boolean = false
    ) : MessageDataEntity
}