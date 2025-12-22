package com.messenger.jaber.navigation.routers

import com.messenger.jaber.feature.presentation.InitRouter
import com.messenger.jaber.navigation.SignInRoute
import com.messenger.jaber.navigation.base.AppRouter
import javax.inject.Inject

class InitRouterImpl @Inject constructor(
    private val appRouter: AppRouter
) : InitRouter {
    override fun launchSignIn() {
        appRouter.restart(SignInRoute)
    }

    override fun navigateUp() {
        appRouter.goBack()
    }

}