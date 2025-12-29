package com.messenger.jaber.feature.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.messenger.core.essentials.Container
import com.messenger.core.essentials.exceptions.handler.ExceptionHandler
import com.messenger.core.essentials.map
import com.messenger.jaber.domain.IsAuthorizedUseCase
import com.messenger.jaber.domain.ShowRandomKeyFeatureUseCase
import com.messenger.jaber.domain.entities.KeyFeature
import com.messenger.jaber.domain.entities.ShowKeyFeatureResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InitViewModel @Inject constructor(
    showRandomKeyFeature: ShowRandomKeyFeatureUseCase,
    private val router: InitRouter,
    private val isAuthorizedUseCase: IsAuthorizedUseCase,
    private val exceptionHandler: ExceptionHandler
) : ViewModel() {

    private val vmStateFlow = MutableStateFlow(ViewModelState())

    private val keyFeatureFlow: Flow<Container<KeyFeature>> = flow {
        showRandomKeyFeature().collect { result ->
            when (result) {
                is ShowKeyFeatureResult.Show ->
                    emit(Container.Success(result.keyFeature))

                ShowKeyFeatureResult.Skip -> authorize()
            }
        }
    }

    val stateFlow: StateFlow<Container<State>> = combine(
        keyFeatureFlow,
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
                authorize()
            } catch (e: Exception) {
                ensureActive()
                hideProgress()
                exceptionHandler.handleException(e)
            }
        }
    }

    private suspend fun authorize() {
        val isAuthorized = isAuthorizedUseCase.invoke()
        if (isAuthorized) {
            //todo launch main screen
        } else {
            router.launchSignIn()
        }
    }

    private fun showProgress() {
        vmStateFlow.update { it.copy(isCheckAuthInProgress = true) }
    }

    private fun hideProgress() {
        vmStateFlow.update { it.copy(isCheckAuthInProgress = false) }
    }

    private data class ViewModelState(
        val isCheckAuthInProgress: Boolean = false
    )

    data class State(
        val keyFeature: KeyFeature,
        val isCheckAuthInProgress: Boolean
    )
}