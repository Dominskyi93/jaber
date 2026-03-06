package com.messenger.jaber.feature.chat_room.presentation

import androidx.lifecycle.SavedStateHandle
import com.elveum.container.Container
import com.elveum.container.containerMap
import com.elveum.container.reducer.containerToReducer
import com.messenger.jaber.core.presentation.WithMviState
import com.messenger.jaber.core.presentation.base.AbstractViewModel
import com.messenger.jaber.feature.chat_room.domain.GetMessagesUseCase
import com.messenger.jaber.feature.chat_room.domain.entities.Message
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ChatRoomVM @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getMessagesUseCase: GetMessagesUseCase
) : AbstractViewModel(), WithMviState<ChatRoomVM.State> {

    val chatId: StateFlow<String?> =
        savedStateHandle.getStateFlow("chatId", null)

    val reducer = getMessagesUseCase(chatId.value ?: "")
        .containerMap { it.toImmutableList() }
        .containerToReducer(
            initialState = ::StateImpl,
            nextState = StateImpl::copy
        )
    val stateFlow: StateFlow<Container<State>> = reducer.stateFlow

    interface State {
        val messages: ImmutableList<Message>
//        val myUId: String
    }

    data class StateImpl(
        override val messages: ImmutableList<Message>,
//        override val myUId: String
    ) : State
}