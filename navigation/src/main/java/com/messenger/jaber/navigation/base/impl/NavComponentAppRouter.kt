package com.messenger.jaber.navigation.base.impl

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.messenger.core.essentials.datetime.DateTimeProvider
import com.messenger.jaber.navigation.Route
import com.messenger.jaber.navigation.base.AppRouter
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class NavComponentAppRouter @Inject constructor(
    private val dateTimeProvider: DateTimeProvider
) : AppRouter {

    private var navController: NavController? = null
    private val commands = mutableListOf<(NavController) -> Unit>()
    private var lastActionTimestampMillis = 0L

    override fun launch(route: Route) = execute { navController ->
        navController.navigate(route)
    }

    override fun restart(route: Route) = execute { navController ->
        navController.navigate(route) {
            popUpTo(0)
        }
    }

    override fun goBack() = execute { navController ->
        navController.navigateUp()
    }

    override fun replace(route: Route) = execute { navController ->
        navController.navigate(route) {
            navController.currentBackStackEntry?.getRouteClass()?.let { currentRouteClass ->
                navController.navigate(route) {
                    popUpTo(currentRouteClass) {
                        inclusive = true
                    }
                }
            }
        }
    }

    fun setNavController(navController: NavController?) {
        this.navController = navController
        if (navController != null) {
            commands.toList().forEach { command ->
                command.invoke(navController)
            }
            commands.clear()
        }
    }

    private fun execute(command: (NavController) -> Unit) {
        val now = dateTimeProvider.currentTimeMillis()
        if (now - lastActionTimestampMillis <= DEBOUNCE_PERIOD_MILLIS) return

        lastActionTimestampMillis = now

        navController?.let {
            command(it)
        } ?: commands.add(command)
    }

    @HiltViewModel
    class VM @Inject constructor(
        val appRouter: NavComponentAppRouter
    ) : ViewModel()

    companion object {
        @Composable
        fun get(): NavComponentAppRouter {
            return hiltViewModel<VM>().appRouter
        }
    }
}

private const val DEBOUNCE_PERIOD_MILLIS = 500