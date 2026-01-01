package com.messenger.jaber.signin.domain.exceptions

import com.messenger.jaber.signin.domain.resources.SignInStringProvider

class InvalidCredentialsException() : AbstractSignInAppException("Invalid login or password") {
    override fun getLocalizedErrorMessage(stringProvider: SignInStringProvider): String {
        return stringProvider.invalidCredentialsError
    }
}