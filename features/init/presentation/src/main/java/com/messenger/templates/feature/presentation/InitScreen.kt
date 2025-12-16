package com.messenger.templates.feature.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.messenger.core.essentials.Container
import com.messenger.core.essentials.exceptions.ConnectionExceptionAbstractCore
import com.messenger.core.theme.Dimens
import com.messenger.core.theme.MediumVerticalSpace
import com.messenger.core.theme.components.ContainerView
import com.messenger.core.theme.components.ProgressButton
import com.messenger.templates.domain.entities.KeyFeature

@Composable
fun InitScreen(viewModel: InitViewModel = hiltViewModel()) {
    val container by viewModel.stateFlow.collectAsStateWithLifecycle()

    ContainerView(
        container = container,
        modifier = Modifier.fillMaxSize(),
        onTryAgainAction = {}
    ) { state ->
        InitContent(
            state = state,
            onLetsGoAction = viewModel::letsGo
        )
    }
}

@Composable
fun InitContent(state: InitViewModel.State, onLetsGoAction: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.MediumPadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val keyFeature = state.keyFeature
        Text(
            text = keyFeature.title,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )

        MediumVerticalSpace()

        Text(
            text = keyFeature.description,
            textAlign = TextAlign.Center
        )

        MediumVerticalSpace()

        ProgressButton(
            isInProgress = state.isCheckAuthInProgress,
            text = stringResource(R.string.let_s_go),
            onClick = onLetsGoAction
        )

    }
}

@Preview(showBackground = true)
@Composable
fun InitContentPreview() {
    InitContent(
        InitViewModel.State(
            KeyFeature(
                0L,
                "DFHsdfh sdf",
                "sldkgsd sdfg sdf gsdf gsdf gsdfgsd fgsdf g"
            ),
            false
        )
    ) {}
}

@Preview
@Composable
private fun SuccessContainerView() {
    ContainerView(
        container = Container.Success("twest test test"),
        onTryAgainAction = {}
    ) { value ->
        Text(
            text = value
        )
    }
}

@Preview
@Composable
private fun PendingContainerView() {
    ContainerView<String>(
        container = Container.Loading,
        onTryAgainAction = {}) { value ->
        Text(
            text = value
        )
    }
}

@Preview
@Composable
private fun ErrorContainerView() {
    ContainerView<String>(
        container = Container.Error(ConnectionExceptionAbstractCore()),
        onTryAgainAction = {}) { value ->
        Text(
            text = value
        )
    }
}