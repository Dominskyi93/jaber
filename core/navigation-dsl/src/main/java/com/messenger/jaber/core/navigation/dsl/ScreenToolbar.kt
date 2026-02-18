package com.messenger.jaber.core.navigation.dsl

import androidx.compose.ui.graphics.vector.ImageVector

sealed class ScreenToolbar {

    data object Hidden : ScreenToolbar()

    data class Default(
        val title: String,
        val action: Action? = null
    ) : ScreenToolbar()
}

data class Action(
    val icon: ImageVector,
    val onClick: () -> Unit
)