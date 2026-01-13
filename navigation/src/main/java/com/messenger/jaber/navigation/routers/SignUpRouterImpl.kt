package com.messenger.jaber.navigation.routers

import android.content.Context
import com.messenger.jaber.features.signup.presentation.SignUpRouter
import com.messenger.jaber.navigation.CongratsRoute
import com.messenger.jaber.navigation.SignInRoute
import com.messenger.jaber.navigation.base.AppRouter
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SignUpRouterImpl @Inject constructor(
    private val appRouter: AppRouter,
    @ApplicationContext private val context: Context
) : SignUpRouter {
    override fun navigateUp() {
        appRouter.goBack()
    }

    override fun launchMain() {
        TODO()
    }

    override fun launchSignIn() {
        appRouter.restart(SignInRoute)
    }

    override fun launchCongrats() {
        appRouter.restart(CongratsRoute)
    }
}