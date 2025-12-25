package com.messenger.jaber.signin.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.messenger.core.theme.components.ContainerView
import com.messenger.jaber.core.navigation.dsl.ScreenScope
import com.messenger.jaber.core.navigation.dsl.ScreenToolbar

@Composable
fun ScreenScope.signInScreen() {
    toolbar = ScreenToolbar.Default(stringResource(R.string.signinscreen))
    content {
        Box {
            SignInContent()
        }
    }
}

@Composable
fun BoxScope.SignInContent(
    viewModel: SignInVM = hiltViewModel()
) {
    val container by viewModel.stateFlow.collectAsState()

    ContainerView(
        container = container,
        onTryAgainAction = {}
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "SignInScreen, authorize = $container"
            )
        }
    }
}