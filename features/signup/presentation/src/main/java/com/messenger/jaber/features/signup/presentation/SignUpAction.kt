package com.messenger.jaber.features.signup.presentation

import com.messenger.jaber.features.signup.domain.entities.InputField
import com.messenger.jaber.features.signup.domain.entities.NewAccount

sealed interface SignUpAction {

    data class SignUp(
        val account: NewAccount
    ) : SignUpAction

    data class Validate(
        val account: NewAccount
    ) : SignUpAction

    data class ClearError(
        val field: InputField<*>
    ) : SignUpAction

    data class EnableErrorMessages(
        val field: InputField<*>
    ) : SignUpAction
}