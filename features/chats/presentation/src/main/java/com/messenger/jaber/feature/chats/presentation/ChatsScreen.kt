package com.messenger.jaber.feature.chats.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.elveum.container.Container
import com.elveum.container.errorContainer
import com.elveum.container.successContainer
import com.messenger.core.essentials.entities.Id
import com.messenger.core.essentials.exceptions.ConnectionException
import com.messenger.core.theme.Dimens
import com.messenger.core.theme.components.AvatarImageView
import com.messenger.core.theme.components.ContainerView
import com.messenger.core.theme.components.ProgressButton
import com.messenger.jaber.core.navigation.dsl.ScreenScope
import com.messenger.jaber.core.navigation.dsl.ScreenToolbar
import com.messenger.jaber.feature.chats.domain.entities.Chat
import com.messenger.jaber.feature.chats.domain.entities.hasUnreadMessages
import kotlinx.collections.immutable.ImmutableList

fun ScreenScope.chatsScreen() {
    val viewModel = viewmodel(ChatsViewModel::class)
    toolbar = ScreenToolbar.Default("Chats screen")

    content {
        val container by viewModel.stateFlow.collectAsStateWithLifecycle()

        ContainerView(
            container = container,
            enablePullToRefresh = true,
            modifier = Modifier.fillMaxSize(),
            onTryAgainAction = {}
        ) { state ->
            Box(Modifier.fillMaxSize()) {
                ChatsContent(
                    state = state,
                    onAction = viewModel::executeAction
                )
            }
        }
    }
}

@Composable
fun BoxScope.ChatsContent(
    state: ChatsViewModel.State,
    onAction: (ChatsAction) -> Unit = {}
) {
    val chats = state.chats

    if (chats.isNotEmpty()) {
        ChatsList(
            chats = chats,
            onDeleteChat = { onAction(ChatsAction.DeleteChat(it)) }
        )
    } else {
        EmptyChats()
    }
}

@Composable
fun ChatsList(
    chats: ImmutableList<Chat>,
    onDeleteChat: (chatId: Id) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            items(
                items = chats,
                key = { it.id.value }
            ) { chat ->
                ChatItem(
                    chat = chat,
                    onDeleteChat = { onDeleteChat(chat.id) },
                    modifier = Modifier.animateItem()
                )
            }
        }
    }
}

@Composable
fun BoxScope.EmptyChats(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Dimens.MediumPadding),
        modifier = modifier
            .align(Alignment.Center)
            .padding(Dimens.MediumPadding)
    ) {
        Icon(
            imageVector = Icons.Default.Mail,
            contentDescription = stringResource(R.string.empty_chats),
            tint = MaterialTheme.colorScheme.outline,
            modifier = Modifier.size(Dimens.MediumImageSize)
        )

        Text(
            text = stringResource(R.string.empty_chats_message),
            textAlign = TextAlign.Center
        )

        ProgressButton(
            isInProgress = false,
            text = "Create chat"
        ) { }
    }
}

@Composable
fun ChatItem(
    chat: Chat,
    onDeleteChat: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(Dimens.SmallPadding),
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .unreadMessagesBackground(
                chat.hasUnreadMessages,
                MaterialTheme.colorScheme.secondaryContainer
            )
            .border(1.dp, Black, RoundedCornerShape(4.dp))
            .clickable {

            }
            .padding(
                vertical = Dimens.ExtraSmallPadding,
                horizontal = Dimens.SmallPadding
            )
    ) {

        AvatarImageView(
            name = chat.title,
            imageSource = chat.imageSource,
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(1f)
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(Dimens.TinySpace),
            modifier = Modifier
                .weight(1f)
                .align(Alignment.Top)
        ) {
            Text(
                text = chat.title,
                maxLines = 1,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold
            )

            chat.lastMessage?.let {
                Text(
                    text = it,
                    maxLines = 1,
                    fontSize = Dimens.BadgeTinyTextSize,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        if (chat.hasUnreadMessages) {
            val countMessages =
                if (chat.unreadMessageCount <= 9) "${chat.unreadMessageCount}" else "9+"

            Text(
                text = countMessages,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .size(Dimens.BadgeMediumSize)
                    .background(
                        color = MaterialTheme.colorScheme.tertiaryContainer,
                        shape = CircleShape
                    )
            )
        }

        Box {
            var expanded by remember { mutableStateOf(false) }

            IconButton(
                onClick = {
                    expanded = true
                }
            ) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "Actions for ${chat.title}",
                    tint = MaterialTheme.colorScheme.outline
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Delete Chat") },
                    onClick = {
                        onDeleteChat()
                        expanded = false
                    }
                )
            }
        }

    }
}

private fun Modifier.unreadMessagesBackground(hasUnreadMessages: Boolean, color: Color): Modifier {
    return if (hasUnreadMessages) {
        background(color)
    } else {
        this
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
private fun ChatItemPreview(
    chat: Chat = Chat.Default(
        id = Id("1"),
        title = "Violeta",
        lastMessage = "Pryvit, yak spravy?",
        unreadMessageCount = 2
    )
) {
    ChatItem(
        chat = chat
    )
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