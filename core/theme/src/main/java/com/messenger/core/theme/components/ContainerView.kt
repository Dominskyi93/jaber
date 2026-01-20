package com.messenger.core.theme.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.elveum.container.Container
import com.messenger.core.essentials.exceptions.mapper.ExceptionToMessageMapper
import com.messenger.core.theme.MediumVerticalSpace
import com.messenger.core.theme.R

@Composable
fun <T> ContainerView(
    container: Container<T>,
    modifier: Modifier = Modifier,
    enablePullToRefresh: Boolean = false,
    onTryAgainAction: () -> Unit,
    exceptionToMessageMapper: ExceptionToMessageMapper = ExceptionToMessageMapper,
    content: @Composable (T) -> Unit
) {
    Box(modifier.fillMaxSize()) {
        container.fold(
            onPending = {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            },
            onError = { exception ->
                val message = exceptionToMessageMapper.getLocalizedMessage(exception)
                ErrorContainerView(message) {
                    onTryAgainAction()
                }
            },
            onSuccess = { value ->
                if (enablePullToRefresh) {
                    PullToRefreshBox(
                        isRefreshing = isLoadingInBackground,
                        onRefresh = { reload(silently = true) },
                        modifier = modifier.fillMaxSize()
                    ) {
                        content(value)
                    }
                } else {
                    content(value)
                }
            }
        )
    }
}

@Composable
private fun BoxScope.ErrorContainerView(
    message: String,
    onTryAgainAction: () -> Unit
) {
    Column(
        modifier = Modifier.align(Alignment.Center),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = message,
            textAlign = TextAlign.Center
        )

        MediumVerticalSpace()

        Button(onClick = onTryAgainAction) {
            Text(stringResource(R.string.try_again))
        }
    }
}