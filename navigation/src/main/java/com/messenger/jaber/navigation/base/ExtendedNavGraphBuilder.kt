package com.messenger.jaber.navigation.base

import com.messenger.jaber.core.navigation.dsl.ScreenScope
import com.messenger.jaber.navigation.Route
import kotlin.reflect.KClass

interface ExtendedNavGraphBuilder {
    fun <T : Route> composable(
        routeClass: KClass<T>,
        content: ScreenScope.(T) -> Unit
    )
}

inline fun <reified T : Route> ExtendedNavGraphBuilder.composable(
    noinline content: ScreenScope.(T) -> Unit
) = composable(T::class, content)