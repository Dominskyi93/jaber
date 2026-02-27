package com.messenger.jaber.settings.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.messenger.jaber.core.navigation.dsl.ScreenScope
import com.messenger.jaber.core.navigation.dsl.ScreenToolbar

fun ScreenScope.settingsScreen() {
    toolbar = ScreenToolbar.Default("Settings")
    content {
        SettingsContent()
    }
}

@Composable
fun SettingsContent(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = "Settings"
        )
    }
}