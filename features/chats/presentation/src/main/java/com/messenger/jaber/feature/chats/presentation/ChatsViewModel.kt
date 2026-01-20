package com.messenger.jaber.feature.chats.presentation

import com.elveum.container.Container
import com.elveum.container.containerMap
import com.elveum.container.reducer.containerToReducer
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
            initialState = ::StateImpl,
            nextState = StateImpl::copy
        )

    val stateFlow: StateFlow<Container<State>> = reducer.stateFlow

    fun executeAction(action: ChatsAction) = when (action) {
        is ChatsAction.DeleteChat -> deleteChat(chatId = action.chatId)
        else -> {}
    }

    private fun deleteChat(chatId: Id) = launch {
        try {
            disableChat(chatId)
            deleteChatUseCase(chatId = chatId)
//            removeChatFromState(chatId = chatId)
        } finally {
            enableChat(chatId)
        }
    }

    private fun enableChat(chatId: Id) = reducer.updateState { oldState ->
        oldState.copy(disabledChatIds = oldState.disabledChatIds - chatId)
    }

    private fun disableChat(chatId: Id) = reducer.updateState { oldState ->
        oldState.copy(disabledChatIds = oldState.disabledChatIds + chatId)
    }

    private fun removeChatFromState(chatId: Id) = reducer.updateState { oldState ->
        oldState.copy(
            originChats = oldState.chats.filter { it.id != chatId }
        )
    }

    interface State {
        val chats: ImmutableList<UiChat>
    }

    data class StateImpl(
        val originChats: List<Chat>,
        val disabledChatIds: Set<Id> = emptySet()
    ) : State {
        override val chats: ImmutableList<UiChat> = originChats
            .map { originChat ->
                UiChat(
                    origin = originChat,
                    isEnabled = !disabledChatIds.contains(originChat.id)
                )
            }.toImmutableList()
    }
}