package com.messenger.jaber.core.presentation

import com.elveum.container.Container
import com.elveum.container.reducer.ContainerReducer
import com.elveum.container.reducer.combineContainersToReducer
import com.elveum.container.reducer.toContainerReducer
import com.elveum.container.successContainer
import com.messenger.jaber.core.presentation.base.ViewModelMixin
import com.messenger.jaber.core.presentation.base.getMixinState
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

interface WithMviState<State> : ViewModelMixin, WithCommonDependencies {

    enum class HideProgressPolicy {
        ON_FINALLY,
        ON_ERROR
    }

    /**
     * Responsible for displaying the progress bar
     */
    val progressStateFlow: StateFlow<Boolean> get() = getMixinState().progressStateFlow


    fun launch(
        hideProgressPolicy: HideProgressPolicy = HideProgressPolicy.ON_FINALLY,
        action: suspend () -> Unit
    ) {
        coroutineScope.launch {
            try {
                updateProgress(true)
                action()
            } catch (exception: Exception) {
                ensureActive()
                if (hideProgressPolicy == HideProgressPolicy.ON_ERROR) updateProgress(false)
                exceptionHandler.handleException(exception)
                logger.e(exception)
            } finally {
                if (hideProgressPolicy == HideProgressPolicy.ON_FINALLY) updateProgress(false)
            }
        }
    }

    fun <T> Flow<Container<T>>.containerToReducer(
        initialState: suspend (T, Boolean) -> State,
        nextState: suspend (State, T, Boolean) -> State
    ): ContainerReducer<State> {
        return combineContainersToReducer(
            this,
            progressStateFlow.map(::successContainer),
            scope = coroutineScope,
            started = defaultSharingStarted,
            initialState = initialState,
            nextState = nextState
        )
    }

    fun createReducer(
        initialState: suspend (Boolean) -> State,
        nextState: suspend (State, Boolean) -> State
    ): ContainerReducer<State> {
        return progressStateFlow.toContainerReducer(
            initialState = initialState,
            nextState = nextState,
            scope = coroutineScope,
            started = defaultSharingStarted
        )
    }

    private fun getMixinState() = getMixinState(::MixinState)

    private fun updateProgress(value: Boolean) = with(getMixinState()) {
        progressStateFlow.value = value
    }

    private class MixinState(
        val progressStateFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    )

}

private val defaultSharingStarted = SharingStarted.WhileSubscribed(
    stopTimeoutMillis = 1000,
    replayExpirationMillis = 0
)