package com.messenger.jaber.feature.chats.domain

import com.elveum.container.ListContainerFlow
import com.messenger.jaber.feature.chats.domain.entities.Chat

 interface GetChatsUseCase {
    operator fun invoke(): ListContainerFlow<Chat>
}