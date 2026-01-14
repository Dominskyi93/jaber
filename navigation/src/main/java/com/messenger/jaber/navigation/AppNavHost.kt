package com.messenger.jaber.navigation

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import com.messenger.jaber.core.navigation.dsl.ScreenToolbar
import com.messenger.jaber.navigation.base.ExtendedNavGraphBuilder
import com.messenger.jaber.navigation.base.impl.ExtendedNavGraphBuilderImpl
import com.messenger.jaber.navigation.base.impl.ExtendedNavStoreImpl
import com.messenger.jaber.navigation.base.impl.NavComponentAppRouter
import kotlinx.coroutines.awaitCancellation

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    startDestination: Route = InitRoute,
    nanGraphBuilder: ExtendedNavGraphBuilder.() -> Unit = {}
) {
    val navController = rememberNavController()
    val context = LocalContext.current
    val navStore = remember { ExtendedNavStoreImpl(context) }
    val navGraph = remember {
        navController.createGraph(startDestination) {
            with(ExtendedNavGraphBuilderImpl(this, navStore)) {
                buildAppNavGraph()
                nanGraphBuilder()
            }
        }
    }
    var showBackButton by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        val navigator = navController
            .navigatorProvider
            .getNavigator(ComposeNavigator::class.java)
        navigator.backStack.collect { backStack ->
            navStore.onBackStackChanged(backStack)
            showBackButton = backStack.size > 1
        }
    }

    val appRouter = NavComponentAppRouter.get()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(Unit) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            try {
                appRouter.setNavController(navController)
                awaitCancellation()
            } finally {
                appRouter.setNavController(null)
            }
        }
    }

    Scaffold(
        topBar = {
            val toolbar = navStore.screen.toolbar
            if (toolbar is ScreenToolbar.Default) {
                AppToolBar(
                    title = toolbar.title,
                    showBackButton = showBackButton,
                    onBackPressed = { navController.navigateUp() },
                )
            }
        },
        modifier = modifier
    ) { paddingValues ->
        val topPadding = animateDpAsState(paddingValues.calculateTopPadding())
        NavHost(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = topPadding.value),
            navController = navController,
            graph = navGraph
        )
    }
}