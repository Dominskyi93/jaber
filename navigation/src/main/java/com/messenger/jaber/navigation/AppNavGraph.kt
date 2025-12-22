package com.messenger.jaber.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.messenger.jaber.feature.presentation.InitScreen
import com.messenger.jaber.signin.presentation.SignInScreen

fun NavGraphBuilder.buildAppNavGraph(
    navController: NavController
) {
    composable<InitRoute> {
        InitScreen()
    }
    composable<SignInRoute> {
        SignInScreen()
    }
}