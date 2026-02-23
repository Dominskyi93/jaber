package com.messenger.jaber.glue.chats.mappers

import com.messenger.core.essentials.entities.Id
import com.messenger.core.essentials.entities.ImageSource
import com.messenger.jaber.data.rooms.entities.RoomDataEntity
import com.messenger.jaber.feature.chats.domain.entities.Chat
import javax.inject.Inject

interface ChatMapper {
    fun mapToChat(roomDataEntity: RoomDataEntity): Chat

    class Default @Inject constructor() : ChatMapper {
        override fun mapToChat(roomDataEntity: RoomDataEntity): Chat = with(roomDataEntity) {
            return Chat.Default(
                id = Id(id),
                title = title,
                userIds = userIds.map { Id(it) },
                imageSource = if (imageSource == null) {
                    ImageSource.Empty
                } else {
                    ImageSource.Remote(imageSource!!)
                },
                lastMessage = lastMessage,
                unreadMessageCount = unreadMessageCount
            )
        }

    }
}