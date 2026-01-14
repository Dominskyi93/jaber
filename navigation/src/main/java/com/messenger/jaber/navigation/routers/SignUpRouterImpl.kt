package com.messenger.jaber.navigation.routers

import com.messenger.jaber.features.signup.presentation.SignUpRouter
import com.messenger.jaber.navigation.CongratsRoute
import com.messenger.jaber.navigation.base.AppRouter
import javax.inject.Inject

class SignUpRouterImpl @Inject constructor(
    private val appRouter: AppRouter,
) : SignUpRouter {

    override fun goBackToSignIn() {
        appRouter.goBack()
    }

    override fun launchCongrats() {
        appRouter.replace(CongratsRoute)
    }
}