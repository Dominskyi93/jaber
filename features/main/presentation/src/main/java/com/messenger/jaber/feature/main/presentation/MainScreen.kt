package com.messenger.jaber.feature.main.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import com.messenger.jaber.core.navigation.dsl.NavigationButton
import com.messenger.jaber.core.navigation.dsl.ScreenBackHandler
import com.messenger.jaber.core.navigation.dsl.ScreenNavigationBar
import com.messenger.jaber.core.navigation.dsl.ScreenScope
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun ScreenScope.mainScreen(
    vararg tabs: Tab
) {
    val currentIndexFlow = savedStateHandle
        .getMutableStateFlow("currentIndex", 1)

    currentIndexFlow
        .onEach { currentIndex ->
            val buttons = tabs.mapIndexed { index, tab ->
                NavigationButton(
                    icon = tab.icon,
                    label = tab.run { context.label() },
                    onClick = { currentIndexFlow.value = index },
                    isSelected = currentIndex == index
                )
            }
            navigationBar = ScreenNavigationBar.Default(buttons.toImmutableList())

            backHandler = if (currentIndex == 0) {
                ScreenBackHandler.Default
            } else {
                ScreenBackHandler.Custom {
                    currentIndexFlow.value = 1
                }
            }
            val currentTab = tabs[currentIndex]

            SavableScreenScope(currentIndex, this)
                .apply(currentTab.configuration)
        }
        .launchIn(coroutineScope)
}

private class SavableScreenScope(
    val currentIndex: Int,
    val origin: ScreenScope
) : ScreenScope by origin {
    override fun content(block: @Composable (() -> Unit)) {
        origin.content {
            val stateHolder = rememberSaveableStateHolder()
            stateHolder.SaveableStateProvider(currentIndex) {
                block()
            }
        }
    }
}
