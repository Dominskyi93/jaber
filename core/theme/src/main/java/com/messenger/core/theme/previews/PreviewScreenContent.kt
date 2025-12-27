package com.messenger.core.theme.previews

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.messenger.core.theme.material.JaberTheme

@Composable
fun PreviewScreenContent(
    content: @Composable () -> Unit
) {
    JaberTheme {
        Surface(Modifier.fillMaxSize()) {
            content()
        }
    }
}