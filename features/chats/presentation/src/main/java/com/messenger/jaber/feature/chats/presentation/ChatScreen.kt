package com.messenger.jaber.feature.chats.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.messenger.jaber.core.navigation.dsl.ScreenScope
import com.messenger.jaber.core.navigation.dsl.ScreenToolbar

fun ScreenScope.chatScreen() {
    val viewModel = viewModel(ChatViewModel::class)

    toolbar = ScreenToolbar.Default(
        title = "Chat"
    )

    content {
        ChatContent()
    }
}

@Composable
fun ChatContent(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "ChatScreen"
        )
    }
}