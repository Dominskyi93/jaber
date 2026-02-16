package com.messenger.jaber.navigation.routers

import com.messenger.jaber.navigation.SignUpRoute
import com.messenger.jaber.navigation.base.AppRouter
import com.messenger.jaber.navigation.base.launchMainFlow
import com.messenger.jaber.signin.presentation.SignInRouter
import javax.inject.Inject

class SignInRouterImpl @Inject constructor(
    private val appRouter: AppRouter,
) : SignInRouter {

    override fun navigateUp() {
        appRouter.goBack()
    }

    override fun launchMain() {
        appRouter.launchMainFlow()
    }

    override fun launchSignUp() {
        appRouter.launch(SignUpRoute)
    }
}