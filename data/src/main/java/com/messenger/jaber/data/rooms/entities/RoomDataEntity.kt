package com.messenger.jaber.data.rooms.entities

interface RoomDataEntity {
    val id: String
    val title: String
    val userIds: List<String>
    val lastMessage: String?
    val imageSource: String?
    val unreadMessageCount: Int

    data class Default(
        override val id: String = "",
        override val title: String = "",
        override val lastMessage: String? = null,
        override val imageSource: String? = null,
        override val unreadMessageCount: Int = 0,
        override val userIds: List<String> = emptyList()
    ) : RoomDataEntity
}