package com.messenger.jaber.feature.chats.presentation

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.elveum.container.Container
import com.elveum.container.errorContainer
import com.elveum.container.successContainer
import com.messenger.core.essentials.exceptions.ConnectionException
import com.messenger.core.theme.Dimens
import com.messenger.core.theme.components.ContainerView
import com.messenger.core.theme.components.ProgressButton
import com.messenger.jaber.core.navigation.dsl.ScreenScope
import com.messenger.jaber.core.navigation.dsl.ScreenToolbar
import com.messenger.jaber.feature.chats.domain.entities.Chat
import kotlinx.collections.immutable.ImmutableList

fun ScreenScope.chatsScreen() {
    val viewModel = viewmodel(ChatsViewModel::class)
    toolbar = ScreenToolbar.Default("Chats screen")

    content {
        val container by viewModel.stateFlow.collectAsStateWithLifecycle()

        ContainerView(
            container = container,
            modifier = Modifier.fillMaxSize(),
            onTryAgainAction = {}
        ) { state ->
            Box(Modifier.fillMaxSize()) {
                ChatsContent(
                    state = state
                )
            }
        }
    }
}

@Composable
fun BoxScope.ChatsContent(
    state: ChatsViewModel.State
) {
    val chats = state.chats

    if (chats.isNotEmpty()) {
        ChatsList(chats)
    } else {
        EmptyChats()
    }
}

@Composable
fun ChatsList(chats: ImmutableList<Chat>, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            items(count = 5) { index ->
                ChatItem(chats[index])
            }
        }
    }
}

@Composable
fun BoxScope.EmptyChats(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.align(Alignment.Center)
    ) {
        Icon(
            imageVector = Icons.Default.Mail,
            contentDescription = stringResource(R.string.empty_chats),
            modifier = Modifier.size(Dimens.MediumImageSize)
        )

        Text(
            text = stringResource(R.string.empty_chats_message)
        )

        ProgressButton(
            isInProgress = false,
            text = "Create chat"
        ) { }
    }
}

@Composable
fun ChatItem(
    chat: Chat
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Black, RoundedCornerShape(4.dp))
            .padding(16.dp)
    ) {
        Text(
            text = chat.title,
            style = MaterialTheme.typography.titleMedium
        )

        chat.lastMessage?.let {
            Spacer(Modifier.height(4.dp))
            Text(
                text = it,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        if (chat.unreadMessageCount > 0) {
            Spacer(Modifier.height(6.dp))
            Text(
                text = "Unread: ${chat.unreadMessageCount}",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}


//@ScreenPreview
//@Composable
//fun ChatsContentPreview() = PreviewScreenContent {
//    ChatsContent(emptyList<Chat>())
//}

@Preview
@Composable
private fun SuccessContainerView() {
    ContainerView(
        container = successContainer("test test test"),
        onTryAgainAction = {}
    ) { value ->
        Text(
            text = value
        )
    }
}

@Preview
@Composable
fun EmptyChatPreview() {
    Box {
        EmptyChats()
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
        container = errorContainer(ConnectionException()),
        onTryAgainAction = {}) { value ->
        Text(
            text = value
        )
    }
}