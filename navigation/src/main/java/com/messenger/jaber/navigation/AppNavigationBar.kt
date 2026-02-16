package com.messenger.jaber.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.messenger.jaber.core.navigation.dsl.ScreenNavigationBar

@Composable
fun AppNavigationBar(
    navigationBar: ScreenNavigationBar.Default,
) {
    NavigationBar(
        modifier = Modifier.fillMaxWidth()
    ) {
        navigationBar.buttons.forEach { button ->
            NavigationBarItem(
                selected = button.isSelected,
                onClick = button.onClick,
                icon = {
                    Icon(
                        imageVector = button.icon,
                        contentDescription = button.label
                    )
                },
                label = {
                    Text(text = button.label)
                }
            )
        }
    }
}
