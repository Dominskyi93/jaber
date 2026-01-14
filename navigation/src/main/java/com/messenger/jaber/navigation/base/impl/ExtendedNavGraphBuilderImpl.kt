package com.messenger.jaber.navigation.base.impl

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.messenger.jaber.core.navigation.dsl.ScreenScope
import com.messenger.jaber.navigation.Route
import com.messenger.jaber.navigation.base.ExtendedNavGraphBuilder
import com.messenger.jaber.navigation.base.ExtendedNavStore
import kotlin.reflect.KClass

class ExtendedNavGraphBuilderImpl(
    private val navGraphBuilder: NavGraphBuilder,
    private val navStore: ExtendedNavStore
) : ExtendedNavGraphBuilder {
    override fun <T : Route> composable(
        routeClass: KClass<T>,
        content: ScreenScope.(T) -> Unit
    ) {
        navStore.registerConfiguration(routeClass, content)
        navGraphBuilder.composable(routeClass) { navEntry ->
            val route = navEntry.toRoute<T>(routeClass)
            navStore.Content(route, navEntry)
        }
    }
}