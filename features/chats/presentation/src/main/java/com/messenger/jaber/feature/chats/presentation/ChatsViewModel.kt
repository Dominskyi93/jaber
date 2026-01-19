package com.messenger.jaber.feature.chats.presentation

import com.elveum.container.Container
import com.elveum.container.containerMap
import com.messenger.core.essentials.entities.Id
import com.messenger.jaber.core.presentation.WithMviState
import com.messenger.jaber.core.presentation.base.AbstractViewModel
import com.messenger.jaber.feature.chats.domain.DeleteChatUseCase
import com.messenger.jaber.feature.chats.domain.GetChatsUseCase
import com.messenger.jaber.feature.chats.domain.entities.Chat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ChatsViewModel @Inject constructor(
    getChatsUseCase: GetChatsUseCase,
    private val deleteChatUseCase: DeleteChatUseCase
) : AbstractViewModel(), WithMviState<ChatsViewModel.State> {


    val reducer = getChatsUseCase()
        .containerMap { it.toImmutableList() }
        .containerToReducer(
            initialState = ::State,
            nextState = State::copy
        )
    val stateFlow: StateFlow<Container<State>> = reducer.stateFlow

    fun executeAction(action: ChatsAction) = when (action) {
        is ChatsAction.DeleteChat -> deleteChat(chatId = action.chatId)
        else -> {}
    }

    private fun deleteChat(chatId: Id) = launch {
        deleteChatUseCase(chatId = chatId)
    }

    data class State(
        val chats: ImmutableList<Chat>,
        val isLoadingInProgress: Boolean = false
    )
}