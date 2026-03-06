package com.messenger.jaber.navigation.routers

import com.messenger.jaber.feature.chat_room.presentation.ChatRoomRouter
import com.messenger.jaber.navigation.base.AppRouter
import javax.inject.Inject

class ChatRoomRouterImpl @Inject constructor(
    private val appRouter: AppRouter
) : ChatRoomRouter {
    override fun navigateUp() {
        appRouter.goBack()
    }
}