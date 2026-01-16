package com.messenger.jaber.signin.presentation

import com.messenger.jaber.core.presentation.WithMviState
import com.messenger.jaber.core.presentation.base.AbstractViewModel
import com.messenger.jaber.signin.domain.SignInUseCase
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
        initialState = State(),
        nextState = State::copy
    )
    val stateFlow = reducer.stateFlow

    fun signIn(credentials: Credentials) = launch {
        try {
            signInUseCase(credentials)
            router.launchMain()
        } catch (e: EmptyFieldException) {
            showEmptyFieldErrorMessage(e.inputField)
        }
    }

    fun signUp() = launch {
        router.launchSignUp()
    }

    fun clearErrorMessages() {
        reducer.update { it.copy(emptyFieldError = null) }
    }

    private fun showEmptyFieldErrorMessage(field: InputField) {
        val emptyErrorMessage = signInStringProvider.emptyFieldError(field)
        val emptyFieldError = EmptyFieldError(field, emptyErrorMessage)
        reducer.update { it.copy(emptyFieldError = emptyFieldError) }
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