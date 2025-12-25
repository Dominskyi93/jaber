@file:OptIn(ExperimentalMaterial3Api::class)

package com.messenger.jaber.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
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
import com.messenger.jaber.navigation.base.ExtendedNavGraphBuilderImpl
import com.messenger.jaber.navigation.base.ExtendedNavStoreImpl
import com.messenger.jaber.navigation.base.NavComponentAppRouter
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

    LaunchedEffect(Unit) {
        val navigator = navController
            .navigatorProvider
            .getNavigator(ComposeNavigator::class.java)
        navigator.backStack.collect { backStack ->
            navStore.onBackStackChanged(backStack)
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
                TopAppBar(
                    title = {
                        Text(toolbar.title)
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                )

            }

        },
        modifier = modifier
    ) { paddingValues ->
        NavHost(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            navController = navController,
            graph = navGraph
        )
    }
}