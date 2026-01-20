package com.messenger.jaber.data.chats

import com.elveum.container.ListContainerFlow
import com.elveum.container.subject.LazyFlowSubject
import com.elveum.container.subject.listenReloadable
import com.elveum.container.successContainer
import com.messenger.core.essentials.entities.Id
import com.messenger.core.essentials.entities.ImageSource
import com.messenger.jaber.data.ChatsDataRepository
import com.messenger.jaber.feature.chats.domain.entities.Chat
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatsDataRepositoryImpl @Inject constructor() : ChatsDataRepository {

    private var chats: List<Chat> = emptyList()

    private val subject = LazyFlowSubject.create {
        delay(1000)
        chats = buildChats()
        emit(chats)
    }

    override fun getChats(): ListContainerFlow<Chat> =
        subject.listenReloadable()

    override suspend fun deleteChat(chatId: Id) {
        delay(1000)
        chats = chats.filterNot { it.id == chatId }

        subject.updateWith(
            successContainer(chats)
        )
    }

    override suspend fun getChatById(chatId: Id): Chat =
        chats.first { it.id == chatId }

    private fun buildChats(): List<Chat> =
        listOf(
            Chat.Default(
                id = Id("1"),
                title = "Support",
                imageSource = ImageSource.Empty,
                lastMessage = "How can we help you?",
                unreadMessageCount = 12
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
                lastMessage = "Lorem ipsum...",
                unreadMessageCount = 1
            )
        )
}
