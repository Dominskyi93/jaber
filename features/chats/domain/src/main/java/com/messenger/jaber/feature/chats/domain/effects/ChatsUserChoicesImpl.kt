package com.messenger.jaber.feature.chats.domain.effects

import com.messenger.core.essentials.dialogs.Dialogs
import com.messenger.core.essentials.dialogs.DialogsConfig
import com.messenger.core.essentials.resources.CoreStringProvider
import com.messenger.jaber.feature.chats.domain.entities.Chat
import com.messenger.jaber.feature.chats.domain.resources.ChatsStringProvider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ChatsUserChoicesImpl @Inject constructor(
    private val dialogs: Dialogs,
    private val chatsStringProvider: ChatsStringProvider,
    private val coreStringProvider: CoreStringProvider
) : ChatsUserChoices {
    override suspend fun confirmChatRemoval(chat: Chat): Boolean {
        val config = DialogsConfig.Default(
            title = chatsStringProvider.confirmDeleteDialogTitle,
            message = chatsStringProvider.confirmDeleteDialogMessage(chat),
            positiveButton = coreStringProvider.delete,
            negativeButton = coreStringProvider.cancel
        )
        return dialogs.showAlertDialog(config)
    }
}