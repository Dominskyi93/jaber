package com.messenger.jaber.navigation.base.impl

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.hilt.lifecycle.viewmodel.HiltViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavBackStackEntry
import androidx.navigation.toRoute
import com.messenger.jaber.core.navigation.dsl.ConfiguredScreen
import com.messenger.jaber.core.navigation.dsl.ScreenBackHandler
import com.messenger.jaber.core.navigation.dsl.ScreenNavigationBar
import com.messenger.jaber.core.navigation.dsl.ScreenScope
import com.messenger.jaber.core.navigation.dsl.ScreenToolbar
import com.messenger.jaber.navigation.Route
import com.messenger.jaber.navigation.base.ExtendedNavStore
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.lifecycle.withCreationCallback
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
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
    override fun <T : Route> Content(route: T, navEntry: NavBackStackEntry) {
        val screen = screens.getOrPut(navEntry.id) {
            createScreen(route, navEntry)
        }
        screen.ScreenContent()
    }

    private fun <T : Route> createScreen(route: T, navEntry: NavBackStackEntry): Screen {
        val screen = Screen(context, navEntry)
        val configuration = configurations[route::class] as Configuration<T>
        configuration.applyTo(screen, route)
        return screen
    }

    private fun createScreen(navEntry: NavBackStackEntry): Screen {
        val routeClass = navEntry.getRouteClass()
        val route = navEntry.toRoute<Route>(routeClass)
        return createScreen(route, navEntry)
    }

    private class Configuration<T : Route>(
        private val content: ScreenScope.(T) -> Unit
    ) {
        fun applyTo(screenScope: ScreenScope, route: T) {
            screenScope.content(route)
        }
    }

    private class Screen(
        override val context: Context,
        private val navEntry: NavBackStackEntry
    ) : ScreenScope {
        override val savedStateHandle: SavedStateHandle
            get() = viewModel(SavedStateHandleViewModel::class).savedStateHandle

        override var toolbar: ScreenToolbar by mutableStateOf(ScreenToolbar.Hidden)

        override var navigationBar: ScreenNavigationBar by mutableStateOf(ScreenNavigationBar.Hidden)

        override var backHandler: ScreenBackHandler by mutableStateOf(ScreenBackHandler.Default)

        private var content: @Composable () -> Unit by mutableStateOf({})

        override val coroutineScope: CoroutineScope get() = navEntry.lifecycleScope

        override fun content(block: @Composable (() -> Unit)) {
            this.content = block
        }

        override fun <T : ViewModel> viewModel(vmClass: KClass<T>): T {
            return getViewModel<T, Nothing>(vmClass)
        }

        override fun <T : ViewModel, F> viewModel(
            vmClass: KClass<T>,
            callback: F.() -> T
        ): T {
            return getViewModel(vmClass, callback)
        }

        @Composable
        fun ScreenContent() {
            content()
        }

        private fun <T : ViewModel, F> getViewModel(
            vmClass: KClass<T>,
            callback: (F.() -> T)? = null
        ): T {
            val factory = HiltViewModelFactory(context, navEntry.defaultViewModelProviderFactory)
            val extras = navEntry.defaultViewModelCreationExtras
            val finalExtras = if (callback == null) {
                extras
            } else {
                extras.withCreationCallback(callback)
            }
            val provider = ViewModelProvider(navEntry.viewModelStore, factory, finalExtras)
            return provider[vmClass]
        }

    }

    @HiltViewModel
    internal class SavedStateHandleViewModel @Inject constructor(
        val savedStateHandle: SavedStateHandle
    ) : ViewModel()
}