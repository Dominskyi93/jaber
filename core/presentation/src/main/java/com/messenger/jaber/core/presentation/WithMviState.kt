package com.messenger.jaber.core.presentation

import com.messenger.core.essentials.Container
import com.messenger.jaber.core.presentation.base.ViewModelMixin
import com.messenger.jaber.core.presentation.base.getMixinState
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

interface WithMviState<State> : ViewModelMixin, WithCommonDependencies {

    enum class HideProgressPolicy {
        ON_FINALLY,
        ON_ERROR
    }

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

    private fun getMixinState() = getMixinState(::MixinState)

    private fun updateProgress(value: Boolean) = with(getMixinState()) {
        progressStateFlow.value = value
    }

    private class MixinState(
        val progressStateFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    )

}