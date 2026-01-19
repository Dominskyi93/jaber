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
        when (container) {

            is Container.Pending -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            is Container.Error -> {
                val message = exceptionToMessageMapper.getLocalizedMessage(container.exception)
                ErrorContainerView(message) {
                    onTryAgainAction()
                }
            }

            is Container.Success -> {
                if (enablePullToRefresh) {
                    PullToRefreshBox(
                        isRefreshing = container.isLoadingInBackground,
                        onRefresh = { onTryAgainAction() },
                        modifier = modifier.fillMaxSize()
                    ) {
                        content(container.value)
                    }
                } else {
                    content(container.value)
                }
            }
        }
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