package com.messenger.jaber.features.signup.domain.exceptions

import com.messenger.jaber.features.signup.domain.entities.InputField
import com.messenger.jaber.features.signup.domain.exceptions.base.AbstractValidationException
import com.messenger.jaber.features.signup.domain.resources.SignUpStringProvider

class LoginAlreadyExistException(
    val login: String
) : AbstractValidationException(
    message = "Login \"$login\" already exists"
) {
    override val inputField = InputField.Login

    override fun getLocalizedErrorMessage(stringProvider: SignUpStringProvider): String {
        return stringProvider.loginAlreadyExistsError(login)
    }
}