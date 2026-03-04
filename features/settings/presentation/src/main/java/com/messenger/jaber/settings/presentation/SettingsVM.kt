package com.messenger.jaber.settings.presentation

import com.messenger.core.essentials.exceptions.ConnectionException
import com.messenger.jaber.core.presentation.WithMviState
import com.messenger.jaber.core.presentation.base.AbstractViewModel
import com.messenger.jaber.settings.domain.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsVM @Inject constructor(
    private val logoutUseCase: LogoutUseCase,
    private val router: SettingsRouter
) : AbstractViewModel(), WithMviState<SettingsVM.State> {

    private val reducer = createReducer(
        initialState = State(),
        nextState = State::copy
    )
    val stateFlow = reducer.stateFlow

    fun logout() = launch(WithMviState.HideProgressPolicy.ON_ERROR) {
        try {
            val isPositiveChoice = logoutUseCase()
            if (isPositiveChoice) {
                router.launchSignIn()
            }
        } catch (e: ConnectionException) {
            updateProgress(false)
        }
    }

//    fun clearErrorMessages() {
//        reducer.update { it.copy(emptyFieldError = null) }
//    }

//    private fun showEmptyFieldErrorMessage(field: InputField) {
//        val emptyErrorMessage = signInStringProvider.emptyFieldError(field)
//        val emptyFieldError = EmptyFieldError(field, emptyErrorMessage)
//        reducer.update { it.copy(emptyFieldError = emptyFieldError) }
//    }

    data class State(
        val isLogoutInProgress: Boolean = false
    )

}