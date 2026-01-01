package com.messenger.jaber.signin.domain.resources

import com.messenger.core.essentials.resources.StringProvider
import com.messenger.jaber.signin.domain.entities.InputField

interface SignInStringProvider : StringProvider {
    val loginFieldName: String
    val passwordFieldName: String
    val invalidCredentialsError: String
    fun emptyFieldError(inputField: InputField): String
}