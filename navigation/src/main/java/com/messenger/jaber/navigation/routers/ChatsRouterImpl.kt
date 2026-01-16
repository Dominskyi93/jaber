package com.messenger.jaber.navigation.routers

import com.messenger.core.essentials.entities.Id
import com.messenger.jaber.feature.chats.presentation.ChatsRouter
import com.messenger.jaber.navigation.base.AppRouter
import javax.inject.Inject

class ChatsRouterImpl @Inject constructor(
    private val appRouter: AppRouter
) : ChatsRouter {
    override fun navigateUp() {
        appRouter.goBack()
    }

    override fun navigateToChat(chatId: Id) {
//        appRouter.launch()
    }
}