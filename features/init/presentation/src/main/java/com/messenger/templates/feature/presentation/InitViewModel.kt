package com.messenger.templates.feature.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.messenger.core.essentials.Container
import com.messenger.core.essentials.containerMap
import com.messenger.core.essentials.exceptions.mapper.ExceptionToMessageMapper
import com.messenger.templates.domain.entities.KeyFeature
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

interface GetKeyFeatureUseCase {
    operator fun invoke(): Flow<Container<KeyFeature>>
}

class InitViewModel @Inject constructor(
    private val getKeyFeatureUseCase: GetKeyFeatureUseCase,
    private val exceptionToMessageMapper: ExceptionToMessageMapper
) : ViewModel() {

    val stateFlow: StateFlow<Container<State>> =
        getKeyFeatureUseCase.invoke()
            .containerMap { keyFeature -> State(keyFeature, false) }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(1000), Container.Loading)

    fun letsGo() {

    }

    data class State(
        val keyFeature: KeyFeature,
        val isCheckAuthInProgress: Boolean
    )
}