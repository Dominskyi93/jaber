package com.messenger.jaber.signin.presentation

import com.messenger.jaber.core.presentation.WithMviState
import com.messenger.jaber.core.presentation.base.AbstractViewModel
import com.messenger.jaber.signin.domain.SignInUseCase
import com.messenger.jaber.signin.domain.entities.AuthResponse
import com.messenger.jaber.signin.domain.entities.Credentials
import com.messenger.jaber.signin.domain.entities.InputField
import com.messenger.jaber.signin.domain.exceptions.EmptyFieldException
import com.messenger.jaber.signin.domain.resources.SignInStringProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInVM @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val signInStringProvider: SignInStringProvider,
    private val router: SignInRouter
) : AbstractViewModel(), WithMviState<SignInVM.State> {

    private val reducer = createReducer(
        initialState = ::State,
        nextState = State::copy
    )
    val stateFlow = reducer.stateFlow

    fun onLaunchTermAndConditions() = router.launchTermsAndConditions()
    fun onLaunchPrivacyPolicy() = router.launchPrivacyPolicy()

    fun signIn(credentials: Credentials) = launch {
        val result = signInUseCase(credentials)
        result.fold(
            onSuccess = {
                router.launchMain()
            },
            onFailure = { e ->
                when (e) {
                    is EmptyFieldException ->
                        showEmptyFieldErrorMessage(e.inputField)

                }
            }
        )
    }

    fun clearErrorMessages() {
        reducer.updateState { it.copy(emptyFieldError = null) }
    }

    private fun showEmptyFieldErrorMessage(field: InputField) {
        val emptyErrorMessage = signInStringProvider.emptyFieldError(field)
        val emptyFieldError = EmptyFieldError(field, emptyErrorMessage)
        reducer.updateState { it.copy(emptyFieldError = emptyFieldError) }
    }

    data class State(
        val isLoginInProgress: Boolean = false,
        val emptyFieldError: EmptyFieldError? = null,
    )

    data class EmptyFieldError(
        val inputField: InputField,
        val message: String
    )
}