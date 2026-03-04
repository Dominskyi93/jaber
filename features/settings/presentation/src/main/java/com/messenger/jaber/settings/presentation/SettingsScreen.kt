package com.messenger.jaber.settings.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.messenger.core.theme.Dimens
import com.messenger.jaber.core.navigation.dsl.ScreenScope
import com.messenger.jaber.core.navigation.dsl.ScreenToolbar
import com.messenger.jaber.feature.settings.presentation.R

fun ScreenScope.settingsScreen() {
    toolbar = ScreenToolbar.Default(context.getString(R.string.settings_title))
    val viewModel: SettingsVM = viewModel(SettingsVM::class)

    content {
        val state by viewModel.stateFlow.collectAsStateWithLifecycle()
        SettingsContent(
            state = state,
            logout = { viewModel.logout() }
        )
    }
}

@Composable
fun SettingsContent(
    state: SettingsVM.State,
    logout: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(Dimens.MediumPadding)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(Dimens.MediumCorner))
                .background(
                    MaterialTheme.colorScheme.primary
                )
                .clickable {
                    logout()
                }
                .padding(Dimens.MediumPadding)
        ) {
            Text(
                text = "Logout",
                color = MaterialTheme.colorScheme.onPrimary
            )
        }

    }
}