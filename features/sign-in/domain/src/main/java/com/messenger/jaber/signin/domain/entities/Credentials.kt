package com.messenger.jaber.signin.domain.entities

import com.messenger.jaber.signin.domain.exceptions.EmptyFieldException

interface Credentials {
    val login: String
    val password: String

    data class Default(
        override val login: String,
        override val password: String
    ) : Credentials
}

/**
 * throws EmptyFieldException
 */
internal fun Credentials.validate() {
    if (login.isBlank()) throw EmptyFieldException(InputField.LOGIN)
    if (password.isBlank()) throw EmptyFieldException(InputField.PASSWORD)
}