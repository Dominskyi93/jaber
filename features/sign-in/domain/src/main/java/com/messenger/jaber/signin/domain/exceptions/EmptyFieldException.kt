package com.messenger.jaber.signin.domain.exceptions

import com.messenger.jaber.signin.domain.entities.InputField
import com.messenger.jaber.signin.domain.resources.SignInStringProvider

class EmptyFieldException(
    val inputField: InputField
) : AbstractSignInAppException("Invalid login or password") {
    override fun getLocalizedErrorMessage(stringProvider: SignInStringProvider): String {
        return stringProvider.emptyFieldError(inputField)
    }
}