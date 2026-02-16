package com.messenger.jaber.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.messenger.jaber.core.navigation.dsl.ScreenNavigationBar
import com.messenger.jaber.core.navigation.dsl.ScreenToolbar

@Composable
internal fun AppScreenScaffold(
    showBackButton: Boolean,
    onBackPressed: () -> Unit,
    toolbar: ScreenToolbar,
    navigationBar: ScreenNavigationBar,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            if (toolbar is ScreenToolbar.Default) {
                AppToolbar(
                    title = "toolbar",
                    showBackButton = showBackButton,
                    onBackPressed = onBackPressed
                )
            }
        },
        bottomBar = {
            if (navigationBar is ScreenNavigationBar.Default) {
                AppNavigationBar(navigationBar)
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            content()
        }
    }
}