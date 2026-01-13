package com.messenger.jaber.navigation.base

import androidx.navigation.NavBackStackEntry
import kotlin.reflect.KClass

fun NavBackStackEntry.getRouteClass(): KClass<*> {
    val routeString = requireNotNull(destination.route)

    val className = if (routeString.contains("/")) {
        routeString.substringBefore("/")
    } else {
        routeString.substringBefore("?")
    }
    return Class.forName(className).kotlin
}