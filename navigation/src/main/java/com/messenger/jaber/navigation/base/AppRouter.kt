package com.messenger.jaber.navigation.base

import com.messenger.jaber.navigation.Route

interface AppRouter {

    fun launch(route: Route)

    fun restart(route: Route)

    fun goBack()
}