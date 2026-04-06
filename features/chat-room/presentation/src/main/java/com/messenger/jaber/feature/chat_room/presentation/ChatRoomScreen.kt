package com.messenger.jaber.feature.chat_room.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.messenger.core.theme.Dimens
import com.messenger.core.theme.components.ContainerView
import com.messenger.jaber.core.navigation.dsl.ScreenScope
import com.messenger.jaber.core.navigation.dsl.ScreenToolbar
import com.messenger.jaber.feature.chat_room.domain.entities.Message
import kotlinx.collections.immutable.ImmutableList

fun ScreenScope.chatRoomScreen() {
    val viewModel = viewModel(ChatRoomVM::class)
    val chatId = viewModel.chatId.value
    toolbar = ScreenToolbar.Default(
        title = "$chatId"
    )

    content {
        val container by viewModel.stateFlow.collectAsStateWithLifecycle()

        ContainerView(
            container = container,
            modifier = Modifier.fillMaxSize(),
            onTryAgainAction = {

            }
        ) { state ->
            Box(Modifier.fillMaxSize()) {
                ChatRoomContent(
                    state = state,
                )
            }
        }
    }
}

@Composable
fun ChatRoomContent(
    state: ChatRoomVM.State
) {
    val messages by remember(state.messages) { mutableStateOf(state.messages) }
    val messageText = rememberTextFieldState("")

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(
            start = Dimens.SmallPadding,
            end = Dimens.SmallPadding,
            bottom = Dimens.SmallPadding
        )
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            if (messages.isEmpty()) {
                Text(
                    text = "No messages yet",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            } else {
                MessagesList(messages)
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            ) {
                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                ) {
                    Icon(
                        imageVector = Icons.Default.AttachFile,
                        contentDescription = "Attach file"
                    )
                }
                MessageTextField(
                    messageTextState = messageText,
                    modifier = Modifier
                )
                if (messageText.text.isNotBlank()) {
                    IconButton(
                        onClick = {

                        },
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Send,
                            contentDescription = "Send message"
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MessagesList(
    messages: ImmutableList<Message>,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.MediumPadding)
    ) {
        LazyColumn {
            items(
                items = messages,
                key = { it.id }
            ) { message ->
                MessageItem(message)
            }
        }
    }
}

@Composable
fun MessageTextField(
    messageTextState: TextFieldState,
    modifier: Modifier = Modifier,
    placeholder: String = "Message"
) {
    BasicTextField(
        state = messageTextState,
        textStyle = TextStyle(
            fontSize = Dimens.BadgeMediumTextSize,
            color = MaterialTheme.colorScheme.onSurface
        ),
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(Dimens.ExtraLargeCorner))
            .border(
                1.dp,
                MaterialTheme.colorScheme.outline,
                RoundedCornerShape(Dimens.ExtraLargeCorner)
            ),
        decorator = { innerTextField ->
            Box(
                modifier = Modifier
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                if (messageTextState.text.isEmpty()) {
                    Text(
                        text = placeholder,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                innerTextField()
            }
        }
    )
}

@Composable
fun MessageItem(
    message: Message,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement =
            if (message.isMyMessage) Arrangement.End else Arrangement.Start
    ) {

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(Dimens.MediumCorner))
                .background(
                    if (message.isMyMessage)
                        MaterialTheme.colorScheme.primary
                    else
                        MaterialTheme.colorScheme.secondaryContainer
                )
                .padding(Dimens.SmallPadding)
        ) {
            Text(
                text = message.text,
                color =
                    if (message.isMyMessage)
                        MaterialTheme.colorScheme.onPrimary
                    else
                        MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
    }
}