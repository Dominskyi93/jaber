package com.messenger.jaber.features.signup.presentation

import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import com.messenger.jaber.features.signup.domain.entities.InputField
import com.messenger.jaber.features.signup.domain.entities.NewAccount
import com.messenger.jaber.features.signup.domain.resources.SignUpStringProvider
import kotlinx.collections.immutable.ImmutableMap

@Stable
class SignUpUiState(
    private val originState: State<SignUpVM.State>,
    private val accountProviderState: State<() -> NewAccount>,
    private val onActionState: State<(SignUpAction) -> Unit>
) : SignUpVM.State {

    private val origin get() = originState.value
    private val onAction get() = onActionState.value
    private val accountProvider get() = accountProviderState.value

    override val errorMessages: ImmutableMap<InputField<*>, String>
        get() = origin.errorMessages
    override val isSignUpInProgress: Boolean
        get() = origin.isSignUpInProgress
    override val stringProvider: SignUpStringProvider
        get() = origin.stringProvider

    val onClearError: (InputField<*>) -> Unit = {
        onAction(SignUpAction.ClearError(InputField.Login))
    }

    val onEnableErrorMessages: (InputField<*>) -> Unit = {
        onAction(SignUpAction.EnableErrorMessages(InputField.Login))
    }

    val onValidate = {
        onAction(SignUpAction.Validate(accountProvider()))
    }
}