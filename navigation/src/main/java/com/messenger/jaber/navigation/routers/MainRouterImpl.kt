package com.messenger.jaber.navigation.routers

import com.messenger.core.essentials.entities.Id
import com.messenger.jaber.feature.main.presentation.MainRouter
import com.messenger.jaber.navigation.base.AppRouter
import javax.inject.Inject

class MainRouterImpl @Inject constructor(
    private val appRouter: AppRouter
) : MainRouter {
    override fun navigateUp() {
        appRouter.goBack()
    }

    override fun navigateToChat(chatId: Id) {
//        appRouter.launch()
    }
}