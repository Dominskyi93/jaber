@file:OptIn(ExperimentalMaterial3Api::class)

package com.messenger.jaber.feature.chats.presentation

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.messenger.core.theme.Dimens
import kotlinx.coroutines.launch

@Composable
fun SearchBottomSheetContent(
    state: ChatsViewModel.State,
    onSearch: (String) -> Unit,
    onDismiss: () -> Unit
) {
    val searchFieldState = rememberSaveable { mutableStateOf("") }
    // TODO: change to users
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    val user = state.newUserInfo
    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        onDismissRequest = {
            scope.launch {
                sheetState.hide()
            }
            onDismiss()
        },
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(Dimens.MediumPadding)
        ) {
            IconButton(
                onClick = {
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            onDismiss()
                        }
                    }
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = null
                )
            }
            SearchTextField(
                valueState = searchFieldState,
                onSearch = { text ->
                    onSearch(text)
                }
            )
            user?.let {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .border(1.dp, Color.Black, RoundedCornerShape(Dimens.MediumCorner))
                        .padding(Dimens.MediumPadding)
                ) {
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .fillMaxHeight()
                    )
                    Text(
                        text = user.lastName,
                        fontSize = Dimens.BadgeMediumTextSize
                    )
                }
            }
        }
    }
}

@Composable
fun SearchTextField(
    valueState: MutableState<String>,
    onSearch: (String) -> Unit,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next
) {
    OutlinedTextField(
        value = valueState.value,
        onValueChange = {
            valueState.value = it
            if (it.length >= 7) {
                onSearch(it)
            }
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
