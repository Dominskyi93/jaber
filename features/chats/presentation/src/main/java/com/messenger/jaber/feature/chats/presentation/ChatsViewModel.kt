package com.messenger.jaber.feature.chats.presentation

import com.messenger.jaber.core.presentation.WithMviState
import com.messenger.jaber.core.presentation.base.AbstractViewModel
import com.messenger.jaber.feature.chats.domain.GetChatsUseCase
import com.messenger.jaber.feature.chats.domain.entities.Chat
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChatsViewModel @Inject constructor(
    getChatsUseCase: GetChatsUseCase
) : AbstractViewModel(), WithMviState<ChatsViewModel.State> {

    val stateFlow = getChatsUseCase().containerToReducer(::State, State::copy)
        .stateFlow


    data class State(
        val chats: List<Chat> = emptyList(),
        val isLoadingInProgress: Boolean = false
    )
}