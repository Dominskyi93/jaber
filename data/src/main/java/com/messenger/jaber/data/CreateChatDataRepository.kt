package com.messenger.jaber.data

interface CreateChatDataRepository {
    suspend fun createChat(title: String, vararg userUIds: String)
}