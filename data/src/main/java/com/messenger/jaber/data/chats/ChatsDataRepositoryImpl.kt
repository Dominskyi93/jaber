package com.messenger.jaber.data.chats

import com.elveum.container.ListContainerFlow
import com.elveum.container.subject.LazyFlowSubject
import com.elveum.container.subject.listenReloadable
import com.messenger.core.essentials.entities.Id
import com.messenger.core.essentials.entities.ImageSource
import com.messenger.jaber.data.ChatsDataRepository
import com.messenger.jaber.feature.chats.domain.entities.Chat
import kotlinx.coroutines.delay
import javax.inject.Inject

class ChatsDataRepositoryImpl @Inject constructor() : ChatsDataRepository {
    override fun getChats(): ListContainerFlow<Chat> {
        return subject.listenReloadable()
    }

    override suspend fun deleteChat(chatId: Id) {
        chats = chats.filter { it.id != chatId }
    }

    override suspend fun getChatById(chatId: Id): Chat {
        return chats.first { it.id == chatId }
    }
}

private val subject = LazyFlowSubject.create {
    delay(1000)
    emit(chats)
}

var chats = listOf(
    Chat.Default(
        id = Id("1"),
        title = "Support",
        imageSource = ImageSource.Empty,
        lastMessage = "How can we help you?",
        unreadMessageCount = 2
    ),
    Chat.Default(
        id = Id("2"),
        title = "Alice",
        imageSource = ImageSource.Empty,
        lastMessage = "See you tomorrow",
        unreadMessageCount = 0
    ),
    Chat.Default(
        id = Id("3"),
        title = "Bob",
        imageSource = ImageSource.Empty,
        lastMessage = "Send the documents",
        unreadMessageCount = 5
    ),
    Chat.Default(
        id = Id("4"),
        title = "Family",
        imageSource = ImageSource.Empty,
        lastMessage = null,
        unreadMessageCount = 0
    ),
    Chat.Default(
        id = Id("5"),
        title = "Work",
        imageSource = ImageSource.Empty,
        lastMessage = "Deadline is today",
        unreadMessageCount = 1
    )
)
