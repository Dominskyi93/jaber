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
        configuration: ScreenScope.(T) -> Unit
    ) {
        navStore.registerConfiguration(routeClass, configuration)
        navGraphBuilder.composable(routeClass) { navEntry ->
            val route = navEntry.toRoute<T>(routeClass)
            navStore.Content(route, navEntry)
        }
    }
}

//origin.addEntryProvider(
//clazz = routeClass,
//content = {
//    route ->
//    val context = LocalContext.current
//    val coroutineScope = rememberCoroutineScope()
//    val viewModelStoreOwner = requireNotNull(LocalViewModelStoreOwner.current)
//    viewModelStoreOwner as HasDefaultViewModelProviderFactory
//
//    val nav3ScreenScope = remember(
//        kotlin.context,
//        kotlinx.coroutines.coroutineScope,
//        viewModelStoreOwner,
//        route
//    ) {
//        Nav3ScreenScope(context, coroutineScope, coroutineScope, route) {
//            configuration(this, route)
//        }
//    }
//
//    AppScreenScaffold(
//        toolbar = nav3ScreenScope.toolbar,
//        navigationBar = nav3ScreenScope.navigationBar,
//        onBackPressed = router::goBack,
//        showBackButton = !router.isRoot(route),
//        modifier = Modifier.fillMaxSize()
//
//    ) { nav3ScreenScope.Content() }
//
//}
//)