package com.messenger.jaber.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph

@Composable
fun AppNavHost(
    startDestination: Route = InitRoute,
    nanGraphBuilder: NavGraphBuilder.() -> Unit = {},
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val navGraph = remember {
        navController.createGraph(startDestination) {
            buildAppNavGraph()
            nanGraphBuilder()
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