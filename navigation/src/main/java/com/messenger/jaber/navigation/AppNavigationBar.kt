package com.messenger.jaber.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.messenger.jaber.core.navigation.dsl.ScreenNavigationBar

@Composable
fun AppNavigationBar(
    navigationBar: ScreenNavigationBar.Default,
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary,
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
                },
                colors = NavigationBarItemDefaults.colors(
                    unselectedIconColor = MaterialTheme.colorScheme.onPrimary,
                    unselectedTextColor = MaterialTheme.colorScheme.onPrimary,
                    selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                    selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    indicatorColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    }
}
