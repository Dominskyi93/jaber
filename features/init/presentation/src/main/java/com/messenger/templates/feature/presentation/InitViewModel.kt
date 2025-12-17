package com.messenger.templates.feature.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.messenger.core.essentials.Container
import com.messenger.core.essentials.exceptions.handler.ExceptionHandler
import com.messenger.core.essentials.map
import com.messenger.templates.domain.IsAuthorizedUseCase
import com.messenger.templates.domain.entities.KeyFeature
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

interface GetKeyFeatureUseCase {
    operator fun invoke(): Flow<Container<KeyFeature>>
}

class InitViewModel @Inject constructor(
    private val getKeyFeatureUseCase: GetKeyFeatureUseCase,
    private val isAuthorizedUseCase: IsAuthorizedUseCase,
    private val exceptionHandler: ExceptionHandler
) : ViewModel() {

    private val vmStateFlow = MutableStateFlow(ViewModelState())

    val stateFlow: StateFlow<Container<State>> = combine(
        getKeyFeatureUseCase.invoke(),
        vmStateFlow
    ) { container, vmState ->
        container.map { keyFeature ->
            State(keyFeature, vmState.isCheckAuthInProgress)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(1000), Container.Loading)

    fun letsGo() {
        viewModelScope.launch {
            try {
                showProgress()
                val isAuthorized = isAuthorizedUseCase.invoke()
                if (isAuthorized) {

                } else {

                }
            } catch (e: Exception) {
                ensureActive()
                hideProgress()
                exceptionHandler.handleException(e)
            }
        }
    }

    private fun showProgress() {
        vmStateFlow.update { it.copy(isCheckAuthInProgress = true) }
    }

    private fun hideProgress() {
        vmStateFlow.update { it.copy(isCheckAuthInProgress = false) }
    }

    data class State(
        val keyFeature: KeyFeature,
        val isCheckAuthInProgress: Boolean
    )

    private data class ViewModelState(
        val isCheckAuthInProgress: Boolean = false
    )
}