package com.messenger.jaber.features.signup.domain.exceptions

import com.messenger.jaber.features.signup.domain.entities.InputField
import com.messenger.jaber.features.signup.domain.exceptions.base.AbstractValidationException
import com.messenger.jaber.features.signup.domain.resources.SignUpStringProvider

class TooLongValueException(
    override val inputField: InputField.Text,
    val value: String
) : AbstractValidationException(
    message = "$inputField is too long (max required chars = ${inputField.maxChars}), current length = ${value.length}"
) {
    override fun getLocalizedErrorMessage(stringProvider: SignUpStringProvider): String {
        return stringProvider.tooLongValueError(inputField)
    }
}