package com.messenger.jaber.signin.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.messenger.core.essentials.Container
import com.messenger.core.essentials.map
import com.messenger.jaber.domain.IsAuthorizedUseCase
import com.messenger.jaber.signin.domain.Authorize
import com.messenger.jaber.signin.domain.GetAuthorizedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SignInVM @Inject constructor(
    getAuthorizedUseCase: GetAuthorizedUseCase
) : ViewModel() {

    val stateFlow: StateFlow<Container<State>> =
        getAuthorizedUseCase.invoke().map { container ->
            container.map { isAuthorized ->
                State(defValue = isAuthorized)
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(1000), Container.Loading)

    class State(
        val defValue: Authorize
    )
}