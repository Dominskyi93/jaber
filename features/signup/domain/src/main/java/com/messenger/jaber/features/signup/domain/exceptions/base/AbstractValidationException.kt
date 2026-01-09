package com.messenger.jaber.features.signup.domain.exceptions.base

import com.messenger.jaber.features.signup.domain.entities.InputField

abstract class AbstractValidationException(
    message: String,
    cause: Throwable? = null
) : AbstractSignUpAppException(
    message, cause
) {
    abstract val inputField: InputField<*>
}