package com.messenger.jaber.navigation.base

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import com.messenger.jaber.core.navigation.dsl.ConfiguredScreen
import com.messenger.jaber.core.navigation.dsl.ScreenScope
import com.messenger.jaber.navigation.Route
import kotlin.reflect.KClass

interface ExtendedNavStore {

    val screen: ConfiguredScreen

    fun onBackStackChanged(backStack: List<NavBackStackEntry>)

    fun <T : Route> registerConfiguration(
        routeClass: KClass<T>,
        content: (ScreenScope.(T) -> Unit)
    )

    @Composable
    fun <T : Route> Content(route: T, id: String)
}