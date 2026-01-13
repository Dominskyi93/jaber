package com.messenger.jaber.navigation.base

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.navigation.NavBackStackEntry
import androidx.navigation.toRoute
import com.messenger.jaber.core.navigation.dsl.ConfiguredScreen
import com.messenger.jaber.core.navigation.dsl.ScreenScope
import com.messenger.jaber.core.navigation.dsl.ScreenToolbar
import com.messenger.jaber.navigation.Route
import kotlin.reflect.KClass

class ExtendedNavStoreImpl(
    private val context: Context
) : ExtendedNavStore {
    private val configurations = mutableMapOf<KClass<*>, Configuration<*>>()
    private var screens = mutableMapOf<String, Screen>()

    override var screen: ConfiguredScreen by mutableStateOf(ConfiguredScreen.Empty)
    override fun onBackStackChanged(backStack: List<NavBackStackEntry>) {

        screens = backStack.associateBy { navEntry -> navEntry.id }
            .mapValues { (id, navEntry) -> screens[id] ?: createScreen(navEntry) }
            .toMutableMap()
        screen = backStack.lastOrNull()
            ?.let { navEntry -> screens[navEntry.id] } ?: ConfiguredScreen.Empty
    }

    override fun <T : Route> registerConfiguration(
        routeClass: KClass<T>,
        content: ScreenScope.(T) -> Unit
    ) {
        configurations[routeClass] = Configuration(content)
    }

    @Composable
    override fun <T : Route> Content(route: T, id: String) {
        val screen = screens.getOrPut(id) {
            createScreen(route)
        }
        screen.ScreenContent()
    }

    private fun <T : Route> createScreen(route: T): Screen {
        val screen = Screen(context)
        val configuration = configurations[route::class] as Configuration<T>
        configuration.applyTo(screen, route)
        return screen
    }

    private fun createScreen(navEntry: NavBackStackEntry): Screen {
        val routeClass = navEntry.getRouteClass()
        val route = navEntry.toRoute<Route>(routeClass)
        return createScreen(route)
    }

    private class Configuration<T : Route>(
        private val content: ScreenScope.(T) -> Unit
    ) {
        fun applyTo(screenScope: ScreenScope, route: T) {
            screenScope.content(route)
        }
    }

    private class Screen(
        override val context: Context
    ) : ScreenScope {
        override fun content(block: @Composable (() -> Unit)) {
            this.content = block
        }

        override var toolbar: ScreenToolbar by mutableStateOf(ScreenToolbar.Hidden)

        private var content: @Composable () -> Unit by mutableStateOf({})

        @Composable
        fun ScreenContent() {
            content()
        }
    }
}