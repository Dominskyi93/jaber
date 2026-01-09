package com.messenger.jaber.features.signup.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.elveum.container.Container
import com.messenger.core.theme.components.ContainerView
import com.messenger.core.theme.components.ProgressButton
import com.messenger.jaber.core.navigation.dsl.ScreenScope
import com.messenger.jaber.core.navigation.dsl.ScreenToolbar

fun ScreenScope.signUpScreen() {
    toolbar = ScreenToolbar.Default(
        context.getString(R.string.signup_sign_up_screen)
    )
    content {
        val viewModel: SignUpVM = hiltViewModel()
        val container: Container<SignUpVM.State> by viewModel.stateFlow.collectAsState()

        ContainerView(
            container = container,
            onTryAgainAction = {}
        ) { state ->
            SignUpContent(
                state = state,
                increment = viewModel::increment
            )
        }
    }
}

@Composable
fun SignUpContent(
    state: SignUpVM.State,
    increment: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = state.title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge
        )

        Text(
            text = "Counter: ${state.counter}",
            style = MaterialTheme.typography.headlineLarge
        )

        ProgressButton(
            isInProgress = state.actionInProgress,
            text = "Increment",
            onClick = increment
        )
    }

}
