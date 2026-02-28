package com.messenger.jaber.feature.chats.presentation

import com.messenger.jaber.core.presentation.WithMviState
import com.messenger.jaber.core.presentation.base.AbstractViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.ImmutableList
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(

) : AbstractViewModel(), WithMviState<ChatViewModel.State> {

//    val reducer = getChatsUseCase()
//        .containerMap { it.toImmutableList() }
//        .containerToReducer(
//            initialState = ::StateImpl,
//            nextState = StateImpl::copy
//        )


    interface State {
        val messages: ImmutableList<UiChat>
    }

    data class StateImpl(
        override val messages: ImmutableList<UiChat>
    ) : State {

    }
}