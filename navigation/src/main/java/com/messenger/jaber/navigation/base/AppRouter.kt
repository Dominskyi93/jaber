package com.messenger.jaber.navigation.base

import com.messenger.jaber.navigation.ChatsRoute
import com.messenger.jaber.navigation.Route
import com.messenger.jaber.navigation.SignInRoute

interface AppRouter {

    fun launch(route: Route)

    fun restart(route: Route)

    fun goBack()

    fun replace(route: Route)
}

fun AppRouter.launchMainFlow() = restart(ChatsRoute)

fun AppRouter.launchAuthFlow() = restart(SignInRoute)