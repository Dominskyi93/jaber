package com.messenger.jaber.feature.chats.presentation

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.elveum.container.Container
import com.elveum.container.errorContainer
import com.elveum.container.successContainer
import com.messenger.core.essentials.exceptions.ConnectionException
import com.messenger.core.theme.components.ContainerView
import com.messenger.core.theme.previews.PreviewScreenContent
import com.messenger.core.theme.previews.ScreenPreview
import com.messenger.jaber.core.navigation.dsl.ScreenScope
import com.messenger.jaber.core.navigation.dsl.ScreenToolbar
import com.messenger.jaber.feature.chats.domain.entities.Chat

fun ScreenScope.chatsScreen() {

    toolbar = ScreenToolbar.Default("Chats screen")
    val viewModel = viewmodel(ChatsViewModel::class)

    content {
        val container by viewModel.stateFlow.collectAsStateWithLifecycle()

        ContainerView(
            container = container,
            modifier = Modifier.fillMaxSize(),
            onTryAgainAction = {}
        ) { state ->
            ChatsContent(
                state = state
            )
        }
    }
}

@Composable
fun ChatsContent(
    state: ChatsViewModel.State
) {
    val chats = state.chats

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            items(count = 5) {index ->
                ChatItem(chats[index])
            }
        }
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