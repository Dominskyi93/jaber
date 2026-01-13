package com.messenger.jaber.navigation

import com.messenger.jaber.feature.presentation.initScreen
import com.messenger.jaber.features.signup.presentation.congratsScreen
import com.messenger.jaber.features.signup.presentation.signUpScreen
import com.messenger.jaber.navigation.base.ExtendedNavGraphBuilder
import com.messenger.jaber.navigation.base.composable
import com.messenger.jaber.signin.presentation.signInScreen

fun ExtendedNavGraphBuilder.buildAppNavGraph() {
    composable<InitRoute> {
        initScreen()
    }
    composable<SignInRoute> {
        signInScreen()
    }
    composable<SignUpRoute> {
        signUpScreen()
    }

    composable<CongratsRoute> {
        congratsScreen()
    }
}