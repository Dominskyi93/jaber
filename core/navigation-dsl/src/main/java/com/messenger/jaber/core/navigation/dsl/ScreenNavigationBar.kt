package com.messenger.jaber.core.navigation.dsl

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.collections.immutable.ImmutableList

@Immutable
sealed class ScreenNavigationBar {
    @Immutable
    data object Hidden : ScreenNavigationBar()

    @Immutable
    data class Default(
        val buttons: ImmutableList<NavigationButton>
    ) : ScreenNavigationBar()
}

data class NavigationButton(
    val icon: ImageVector,
    val label: String,
    val onClick: () -> Unit,
    val isSelected: Boolean = false
)