package com.messenger.jaber.feature.main.presentation

import android.content.Context
import androidx.compose.ui.graphics.vector.ImageVector
import com.messenger.jaber.core.navigation.dsl.ScreenScope

data class Tab(
    val icon: ImageVector,
    val label: Context.() -> String,
    val configuration: ScreenScope.() -> Unit
)