package com.messenger.jaber.features.signup.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.messenger.core.theme.Dimens
import com.messenger.core.theme.components.ProgressButton
import com.messenger.core.theme.previews.ScreenPreview
import com.messenger.jaber.core.navigation.dsl.ScreenScope
import com.messenger.jaber.core.navigation.dsl.ScreenToolbar

fun ScreenScope.congratsScreen() {

    toolbar = ScreenToolbar.Default(context.getString(R.string.signup_congratulations_toolbar))

    content {
        val viewModel = hiltViewModel<CongratsVM>()

        CongratsContent(
            onGoToSignIn = viewModel::goBackToSignIn
        )
    }
}

@Composable
private fun CongratsContent(onGoToSignIn: () -> Unit) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            Dimens.MediumPadding,
            Alignment.CenterVertically
        ),
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.MediumPadding)
    ) {

        Text(
            text = stringResource(R.string.signup_congratulations),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge
        )

        ProgressButton(
            isInProgress = false,
            text = stringResource(R.string.signup_go_to_login),
            onClick = onGoToSignIn
        )

    }
}

@ScreenPreview
@Composable
private fun CongratsPreview() {
    CongratsContent {  }
}