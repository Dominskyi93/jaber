package com.messenger.jaber.features.signup.domain.exceptions

import com.messenger.jaber.features.signup.domain.entities.InputField
import com.messenger.jaber.features.signup.domain.exceptions.base.AbstractValidationException
import com.messenger.jaber.features.signup.domain.resources.SignUpStringProvider

class EmptyFieldException(
    override val inputField: InputField.Text
) : AbstractValidationException(
    message = "$inputField is not be empty"
) {
    override fun getLocalizedErrorMessage(stringProvider: SignUpStringProvider): String {
        return "${inputField.fieldName(stringProvider)} should not be empty"
    }
}