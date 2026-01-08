package com.messenger.jaber.features.signup.domain.exceptions

import com.messenger.jaber.features.signup.domain.entities.InputField
import com.messenger.jaber.features.signup.domain.exceptions.base.AbstractValidationException
import com.messenger.jaber.features.signup.domain.resources.SignUpStringProvider

class InvalidRangeException(
    override val inputField: InputField.Number,
    val value: String
) : AbstractValidationException(
    message = "$inputField should be in range ${inputField.range}."
) {
    override fun getLocalizedErrorMessage(stringProvider: SignUpStringProvider): String {
        return "${inputField.fieldName(stringProvider)} should be in range ${inputField.range}."
    }
}