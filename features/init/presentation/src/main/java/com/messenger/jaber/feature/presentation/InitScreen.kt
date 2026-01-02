package com.messenger.jaber.feature.presentation

import android.content.res.Configuration
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.elveum.container.Container
import com.messenger.core.essentials.entities.Id
import com.messenger.core.essentials.entities.ImageSource
import com.messenger.core.essentials.exceptions.ConnectionException
import com.messenger.core.theme.Dimens
import com.messenger.core.theme.MediumVerticalSpace
import com.messenger.core.theme.components.ContainerView
import com.messenger.core.theme.components.ImageView
import com.messenger.core.theme.components.ProgressButton
import com.messenger.core.theme.previews.PreviewScreenContent
import com.messenger.core.theme.previews.ScreenPreview
import com.messenger.jaber.core.navigation.dsl.ScreenScope
import com.messenger.jaber.core.navigation.dsl.ScreenToolbar
import com.messenger.jaber.domain.entities.KeyFeature
import kotlinx.coroutines.delay

fun ScreenScope.initScreen() {
    toolbar = ScreenToolbar.Hidden
    content {
        val viewModel: InitViewModel = hiltViewModel()
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
}

@Composable
fun InitPortraitContent(
    state: InitViewModel.State,
    onLetsGoAction: () -> Unit
) {
    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        delay(1000)
        scrollState.animateScrollTo(scrollState.maxValue, tween(1000))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
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

        ImageView(
            imageSource = keyFeature.image,
            modifier = Modifier.size(Dimens.LargeImageSize)
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

@Composable
fun InitContent(
    state: InitViewModel.State,
    onLetsGoAction: () -> Unit
) {
    val configuration = LocalConfiguration.current
    if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        InitLandscapeContent(
            state = state,
            onLetsGoAction = onLetsGoAction
        )
    } else {
        InitPortraitContent(
            state = state,
            onLetsGoAction = onLetsGoAction
        )
    }
}

@Composable
fun InitLandscapeContent(
    state: InitViewModel.State,
    onLetsGoAction: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        val keyFeature = state.keyFeature
        Column(
            modifier = Modifier
                .padding(Dimens.MediumPadding)
                .weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
        ImageView(
            imageSource = keyFeature.image,
            modifier = Modifier
                .wrapContentSize()
                .size(Dimens.LargeImageSize)
                .weight(1f)
        )
    }
}

@ScreenPreview
@Composable
fun InitContentPreview() = PreviewScreenContent {
    InitContent(
        InitViewModel.State(
            KeyFeature(
                id = Id.Empty,
                title = "Lorem ipsu",
                description = "Lorem ipsum dolor sit amet, consetet",
                image = ImageSource.Empty
            ),
            false
        )
    ) {}
}

@Preview
@Composable
private fun SuccessContainerView() {
    ContainerView(
        container = com.elveum.container.Container.Success("twest test test"),
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
        container = Container.Pending,
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
        container = Container.Error(ConnectionException()),
        onTryAgainAction = {}) { value ->
        Text(
            text = value
        )
    }
}