package com.messenger.jaber.features.signup.domain.exceptions

import com.messenger.jaber.features.signup.domain.entities.InputField
import com.messenger.jaber.features.signup.domain.exceptions.base.AbstractValidationException
import com.messenger.jaber.features.signup.domain.resources.SignUpStringProvider

class PasswordMismatchException(
) : AbstractValidationException(
    message = "Password mismatch error!"
) {
    override val inputField = InputField.RepeatPassword

    override fun getLocalizedErrorMessage(stringProvider: SignUpStringProvider): String {
        return "Password and Repeat Password must contain the same password value."
    }
}