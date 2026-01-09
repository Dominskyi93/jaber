package com.messenger.jaber.features.signup.presentation

import com.elveum.container.Container
import com.elveum.container.map
import com.elveum.container.successContainer
import com.messenger.jaber.core.presentation.WithMviState
import com.messenger.jaber.core.presentation.base.AbstractViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SignUpVM @Inject constructor(

) : AbstractViewModel(), WithMviState<SignUpVM.State> {

    val stateFlow: MutableStateFlow<Container<State>> =
        MutableStateFlow(successContainer(State()))

    fun increment() = launch {
        stateFlow.update { container ->
            container.map { state ->
                state.copy(counter = state.counter + 1)
            }
        }
    }

    data class State(
        val title: String = "Sign up",
        val actionInProgress: Boolean = false,
        val counter: Int = 0
    )
}