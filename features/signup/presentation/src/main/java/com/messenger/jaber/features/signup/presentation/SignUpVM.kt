package com.messenger.jaber.features.signup.presentation

import com.messenger.core.essentials.flows.throttle
import com.messenger.jaber.core.presentation.WithInitCallback
import com.messenger.jaber.core.presentation.WithMviState
import com.messenger.jaber.core.presentation.base.AbstractViewModel
import com.messenger.jaber.features.signup.domain.SignUpUseCase
import com.messenger.jaber.features.signup.domain.ValidateAccountUseCase
import com.messenger.jaber.features.signup.domain.entities.InputField
import com.messenger.jaber.features.signup.domain.entities.InputFieldValue
import com.messenger.jaber.features.signup.domain.entities.NewAccount
import com.messenger.jaber.features.signup.domain.entities.ValidationResult
import com.messenger.jaber.features.signup.domain.entities.toFieldValues
import com.messenger.jaber.features.signup.domain.exceptions.base.AbstractValidationException
import com.messenger.jaber.features.signup.domain.resources.SignUpStringProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.ImmutableMap
import kotlinx.collections.immutable.toImmutableMap
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SignUpVM @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val validateAccountUseCase: ValidateAccountUseCase,
    private val router: SignUpRouter,
    private val stringProvider: SignUpStringProvider
) : AbstractViewModel(), WithInitCallback, WithMviState<SignUpVM.StateImpl> {

    private val reducer = createReducer(
        initialState = StateImpl(stringProvider = stringProvider),
        nextState = StateImpl::copy
    )

    val stateFlow: StateFlow<State> = reducer.stateFlow

    private val validateRequestsFlow = MutableSharedFlow<NewAccount>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    override suspend fun onInitialized() {
        validateRequestsFlow
            .throttle(VALIDATION_PERIOD_MILLIS)
            .collect(::validate)
    }

    fun executeAction(action: SignUpAction) {
        when (action) {
            is SignUpAction.SignUp -> signUp(action.account)
            is SignUpAction.Validate -> validateRequestsFlow.tryEmit(action.account)
            is SignUpAction.ClearError -> clearError(action.field)
            is SignUpAction.EnableErrorMessages -> enableErrorMessages(action.field)
        }
    }

    private fun signUp(account: NewAccount) = launch(WithMviState.HideProgressPolicy.ON_ERROR) {
        try {
            signUpUseCase.invoke(account)
            router.launchCongrats()
        } catch (e: AbstractValidationException) {
            currentCoroutineContext().ensureActive()
            renderValidationException(account, e)
            throw e // show default error dialog
        }
    }

    private suspend fun validate(account: NewAccount) = launchSync {
        val validationResult = validateAccountUseCase(account)
        reducer.update { oldState ->
            oldState.withNewValidationResult(validationResult)
        }
    }

    private fun clearError(field: InputField<*>) {
        reducer.update { oldState ->
            oldState.clearError(field)
        }
    }

    private fun enableErrorMessages(field: InputField<*>) {
        reducer.update { oldState ->
            oldState.enableErrorMessages(field)
        }
    }

    private fun StateImpl.clearError(field: InputField<*>) = copy(
        allErrorMessages = (allErrorMessages - field)
    )

    private fun StateImpl.enableErrorMessages(field: InputField<*>) = copy(
        fieldsWithEnabledErrors = fieldsWithEnabledErrors + field
    )

    private fun renderValidationException(account: NewAccount, e: AbstractValidationException) {
        reducer.update { oldState ->
            oldState.withValidationException(account, e)
        }
    }

    private fun StateImpl.withValidationException(
        account: NewAccount,
        e: AbstractValidationException
    ) = copy(
        allErrorMessages = allErrorMessages + toErrorMessagePair(e),
        fieldsWithEnabledErrors = account.toFieldValues()
            .map(InputFieldValue<*>::inputField)
            .toSet()
    )

    private fun StateImpl.withNewValidationResult(validationResult: ValidationResult) =
        copy(allErrorMessages = validationResult.toErrorMessagesMap())

    private fun ValidationResult.toErrorMessagesMap(): Map<InputField<*>, String> {
        return when (this) {
            is ValidationResult.Failure -> exceptions.associate(::toErrorMessagePair)
            ValidationResult.Success -> emptyMap()
        }
    }

    private fun toErrorMessagePair(e: AbstractValidationException) =
        e.inputField to e.getLocalizedErrorMessage(stringProvider)

    private suspend fun launchSync(action: suspend () -> Unit) {
        try {
            action()
        } catch (e: Exception) {
            currentCoroutineContext().ensureActive()
            logger.e(e)
        }
    }

    interface State {
        val isSignUpInProgress: Boolean
        val errorMessages: ImmutableMap<InputField<*>, String>
        val stringProvider: SignUpStringProvider
    }

    private data class StateImpl(
        override val isSignUpInProgress: Boolean = false,
        override val stringProvider: SignUpStringProvider,
        val allErrorMessages: Map<InputField<*>, String> = emptyMap(),
        val fieldsWithEnabledErrors: Set<InputField<*>> = emptySet(),
    ) : State {
        override val errorMessages: ImmutableMap<InputField<*>, String> =
            allErrorMessages.filterKeys(fieldsWithEnabledErrors::contains)
                .toImmutableMap()
    }

    internal companion object {
        const val VALIDATION_PERIOD_MILLIS = 1000L
    }
}