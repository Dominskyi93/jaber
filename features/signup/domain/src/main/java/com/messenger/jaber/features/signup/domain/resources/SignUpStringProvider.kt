package com.messenger.jaber.features.signup.domain.resources

import com.messenger.core.essentials.resources.StringProvider
import com.messenger.jaber.features.signup.domain.entities.InputField

interface SignUpStringProvider : StringProvider {
    val loginField: String
    val passwordField: String
    val repeatPasswordField: String
    val firstNameField: String
    val lastNameField: String
    val ageField: String

    val passwordMismatchError: String

    fun loginAlreadyExistsError(login: String): String
    fun emptyFieldError(field: InputField.Text): String
    fun invalidRangeError(field: InputField.Number): String
    fun tooLongValueError(field: InputField.Text): String
    fun tooShortValueError(field: InputField.Text): String
}