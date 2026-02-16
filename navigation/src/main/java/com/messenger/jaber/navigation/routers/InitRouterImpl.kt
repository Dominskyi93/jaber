package com.messenger.jaber.navigation.routers

import com.messenger.jaber.feature.presentation.InitRouter
import com.messenger.jaber.navigation.ChatsRoute
import com.messenger.jaber.navigation.base.AppRouter
import com.messenger.jaber.navigation.base.launchAuthFlow
import com.messenger.jaber.navigation.base.launchMainFlow
import javax.inject.Inject

class InitRouterImpl @Inject constructor(
    private val appRouter: AppRouter
) : InitRouter {
    override fun launchAuthFlow() {
        appRouter.launchAuthFlow()
    }

    override fun launchMainFlow() {
        appRouter.launchMainFlow()
    }

    override fun navigateUp() {
        appRouter.goBack()
    }
}