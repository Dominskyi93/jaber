package com.messenger.jaber.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import com.messenger.jaber.navigation.base.NavComponentAppRouter
import kotlinx.coroutines.awaitCancellation

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    startDestination: Route = InitRoute,
    nanGraphBuilder: NavGraphBuilder.() -> Unit = {}
) {
    val navController = rememberNavController()
    val navGraph = remember {
        navController.createGraph(startDestination) {
            buildAppNavGraph()
            nanGraphBuilder()
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