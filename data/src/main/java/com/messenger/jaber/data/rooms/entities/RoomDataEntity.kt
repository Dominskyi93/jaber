package com.messenger.jaber.data.rooms.entities

import com.messenger.core.essentials.entities.Id
import com.messenger.core.essentials.entities.ImageSource

interface RoomDataEntity {
    val id: Id
    val title: String
    val userIds: List<Id>
    val lastMessage: String?
    val imageSource: ImageSource
    val unreadMessageCount: Int

    data class Default(
        override val id: Id,
        override val title: String,
        override val lastMessage: String?,
        override val imageSource: ImageSource = ImageSource.Empty,
        override val unreadMessageCount: Int = 0,
        override val userIds: List<Id> = emptyList()
    ) : RoomDataEntity
}