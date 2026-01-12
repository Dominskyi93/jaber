package com.messenger.jaber.features.signup.presentation

import androidx.compose.runtime.Stable
import com.messenger.jaber.features.signup.domain.entities.InputField
import com.messenger.jaber.features.signup.domain.entities.NewAccount

@Stable
class SignUpUiState(
    origin: SignUpVM.State,
    accountProvider: () -> NewAccount,
    onAction: (SignUpAction) -> Unit = {}
) : SignUpVM.State by origin {

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