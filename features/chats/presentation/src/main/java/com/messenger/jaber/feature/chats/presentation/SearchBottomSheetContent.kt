@file:OptIn(ExperimentalMaterial3Api::class)

package com.messenger.jaber.feature.chats.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import com.messenger.core.theme.Dimens

@Composable
fun SearchBottomSheetContent(
    state: ChatsViewModel.State,
    onDismiss: () -> Unit
) {
    val searchFieldState = rememberSaveable { mutableStateOf("") }
    // TODO: change to users
    val users = state.chats

    ModalBottomSheet(
        onDismissRequest = onDismiss
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(Dimens.MediumPadding)
        ) {
            SearchTextField(
                valueState = searchFieldState
            )

            LazyColumn() {
                items(
                    items = users,
                    key = { it.id.value }
                ) { item ->
                    // TODO:
//                UserItem(item)
                }
            }
        }
    }
}

@Composable
fun SearchTextField(
    valueState: MutableState<String>,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next
) {
    OutlinedTextField(
        value = valueState.value,
        onValueChange = {
            valueState.value = it
        },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        visualTransformation = visualTransformation,
        label = { Text(text = "Search user") },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        )
    )
}
