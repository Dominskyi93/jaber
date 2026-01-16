package com.messenger.jaber.feature.chats.presentation.resources

import android.content.Context
import com.messenger.jaber.feature.chats.domain.entities.Chat
import com.messenger.jaber.feature.chats.domain.resources.ChatsStringProvider
import com.messenger.jaber.feature.chats.presentation.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ChatsStringProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : ChatsStringProvider {
    override val confirmDeleteDialogTitle: String = context.getString(R.string.chat_removal)

    override fun confirmDeleteDialogMessage(chat: Chat): String {
        return context.getString(R.string.are_you_sure_you_want_to_delete_chat, chat.title)
    }
}